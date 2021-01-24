package world.cepi.bukstom.command

import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.command.*
import org.bukkit.command.defaults.*
import org.bukkit.entity.Player
import org.bukkit.util.StringUtil
import java.util.*
import kotlin.collections.HashMap

open class MinestomCommandMap(private val server: Server) : CommandMap {
    private val knownCommands: MutableMap<String, Command> = HashMap()
    private fun setDefaultCommands() {
        register("bukkit", VersionCommand("version"))
        register("bukkit", ReloadCommand("reload"))
        register("bukkit", PluginsCommand("plugins"))
        register("bukkit", TimingsCommand("timings"))
    }

    fun setFallbackCommands() {
        register("bukkit", HelpCommand())
    }

    /**
     * {@inheritDoc}
     */
    override fun registerAll(fallbackPrefix: String, commands: List<Command>) {
        commands.forEach { register(fallbackPrefix, it) }
    }

    /**
     * {@inheritDoc}
     */
    override fun register(fallbackPrefix: String, command: Command): Boolean {
        return register(command.name, fallbackPrefix, command)
    }

    /**
     * {@inheritDoc}
     */
    override fun register(label: String, fallbackPrefix: String, command: Command): Boolean {
        val label = label.toLowerCase(Locale.ENGLISH).trim { it <= ' ' }
        val fallbackPrefix = fallbackPrefix.toLowerCase(Locale.ENGLISH).trim { it <= ' ' }
        val registered = register(label, command, false, fallbackPrefix)
        val iterator = command.aliases.iterator()
        while (iterator.hasNext()) {
            if (!register(iterator.next(), command, true, fallbackPrefix)) {
                iterator.remove()
            }
        }

        // If we failed to register under the real name, we need to set the command label to the direct address
        if (!registered) {
            command.label = "$fallbackPrefix:$label"
        }

        // Register to us so further updates of the commands label and aliases are postponed until its reregistered
        command.register(this)
        return registered
    }

    /**
     * Registers a command with the given name is possible. Also uses
     * fallbackPrefix to create a unique name.
     *
     * @param label the name of the command, without the '/'-prefix.
     * @param command the command to register
     * @param isAlias whether the command is an alias
     * @param fallbackPrefix a prefix which is prepended to the command for a
     * unique address
     * @return true if command was registered, false otherwise.
     */
    @Synchronized
    private fun register(label: String, command: Command, isAlias: Boolean, fallbackPrefix: String): Boolean {
        knownCommands["$fallbackPrefix:$label"] = command
        if ((command is BukkitCommand || isAlias) && knownCommands.containsKey(label)) {
            // Request is for an alias/fallback command and it conflicts with
            // a existing command or previous alias ignore it
            // Note: This will mean it gets removed from the commands list of active aliases
            return false
        }
        val registered = true

        // If the command exists but is an alias we overwrite it, otherwise we return
        val conflict = knownCommands[label]
        if (conflict != null && conflict.label == label) {
            return false
        }
        if (!isAlias) {
            command.label = label
        }
        knownCommands[label] = command
        return registered
    }

    /**
     * {@inheritDoc}
     */
    @Throws(CommandException::class)
    override fun dispatch(sender: CommandSender, commandLine: String): Boolean {

        val args = commandLine.split(" ").toTypedArray()
        if (args.isEmpty())
            return false

        val sentCommandLabel = args[0].toLowerCase(Locale.ENGLISH)
        val target = getCommand(sentCommandLabel) ?: return false
        try {
            target.timings.startTiming() // Spigot
            // Note: we don't return the result of target.execute as thats success / failure, we return handled (true) or not handled (false)
            target.execute(sender, sentCommandLabel, args.copyOfRange(1, args.size))
            target.timings.stopTiming() // Spigot
        } catch (ex: CommandException) {
            target.timings.stopTiming() // Spigot
            throw ex
        } catch (ex: Throwable) {
            target.timings.stopTiming() // Spigot
            throw CommandException("Unhandled exception executing '$commandLine' in $target", ex)
        }

        // return true as command was handled
        return true
    }

    @Synchronized
    override fun clearCommands() {

        for ((_, value) in knownCommands) {
            value.unregister(this)
        }

        knownCommands.clear()
        setDefaultCommands()
    }

    override fun getCommand(name: String): Command? {
        return knownCommands[name.toLowerCase(Locale.ENGLISH)]
    }

    override fun tabComplete(sender: CommandSender, cmdLine: String): List<String>? {
        return tabComplete(sender, cmdLine, null)
    }

    override fun tabComplete(sender: CommandSender, cmdLine: String, location: Location?): List<String>? {
        val spaceIndex = cmdLine.indexOf(' ')
        if (spaceIndex == -1) {
            val completions = ArrayList<String>()
            val knownCommands: Map<String, Command> = knownCommands
            val prefix = if (sender is Player) "/" else ""
            for ((name, command) in knownCommands) {
                if (!command.testPermissionSilent(sender)) {
                    continue
                }
                if (StringUtil.startsWithIgnoreCase(name, cmdLine)) {
                    completions.add(prefix + name)
                }
            }
            Collections.sort(completions, java.lang.String.CASE_INSENSITIVE_ORDER)
            return completions
        }
        val commandName = cmdLine.substring(0, spaceIndex)
        val target = getCommand(commandName) ?: return null
        if (!target.testPermissionSilent(sender)) {
            return null
        }
        val args = cmdLine.substring(spaceIndex + 1, cmdLine.length).split(" ").dropLastWhile { it.isEmpty() }
            .toTypedArray()
        return try {
            target.tabComplete(sender, commandName, args, location)
        } catch (ex: CommandException) {
            throw ex
        } catch (ex: Throwable) {
            throw CommandException("Unhandled exception executing tab-completer for '$cmdLine' in $target", ex)
        }
    }

    val commands: Collection<Command>
        get() = Collections.unmodifiableCollection(knownCommands.values)

    fun registerServerAliases() {
        val values = server.commandAliases
        for ((alias, commandStrings) in values) {
            if (alias.contains(" ")) {
                server.logger.warning("Could not register alias $alias because it contains illegal characters")
                continue
            }
            val targets: MutableList<String> = ArrayList()
            val bad = StringBuilder()
            for (commandString in commandStrings) {
                val commandArgs = commandString.split(" ").toTypedArray()
                val command = getCommand(commandArgs[0])
                if (command == null) {
                    if (bad.isNotEmpty()) {
                        bad.append(", ")
                    }
                    bad.append(commandString)
                } else {
                    targets.add(commandString)
                }
            }
            if (bad.isNotEmpty()) {
                server.logger.warning("Could not register alias $alias because it contains commands that do not exist: $bad")
                continue
            }

            // We register these as commands so they have absolute priority.
            if (targets.size > 0) {
                knownCommands[alias.toLowerCase(Locale.ENGLISH)] =
                    FormattedCommandAlias(alias.toLowerCase(Locale.ENGLISH), targets.toTypedArray())
            } else {
                knownCommands.remove(alias.toLowerCase(Locale.ENGLISH))
            }
        }
    }

    init {
        setDefaultCommands()
    }
}