package org.bukkit.plugin

import com.google.common.base.Preconditions
import com.google.common.collect.ImmutableSet
import com.google.common.graph.GraphBuilder
import com.google.common.graph.Graphs
import org.bukkit.Server
import org.bukkit.command.PluginCommandYamlParser
import org.bukkit.command.SimpleCommandMap
import org.bukkit.command.defaults.TimingsCommand
import org.bukkit.craftbukkit.command.MinestomCommandMap
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.permissions.Permissible
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault
import org.bukkit.util.FileUtil
import java.io.File
import java.lang.reflect.Constructor
import java.util.*
import java.util.logging.Level
import java.util.regex.Pattern
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet

/**
 * Handles all plugin management from the Server
 */
class MinestomPluginLoader(private val server: Server, private val commandMap: MinestomCommandMap) : PluginManager {
    private val fileAssociations: MutableMap<Pattern, PluginLoader> = HashMap()
    private val plugins: MutableList<Plugin> = ArrayList()
    private val lookupNames: MutableMap<String, Plugin> = HashMap()
    private var dependencyGraph = GraphBuilder.directed().build<String>()
    private var updateDirectory: File? = null
    private val permissions: MutableMap<String, Permission> = HashMap()
    private val defaultPerms: MutableMap<Boolean, MutableSet<Permission>> = LinkedHashMap()
    private val permSubs: MutableMap<String, MutableMap<Permissible, Boolean>> = HashMap()
    private val defSubs: MutableMap<Boolean, MutableMap<Permissible, Boolean>> = HashMap()
    private var useTimings = false

    /**
     * Registers the specified plugin loader
     *
     * @param loader Class name of the PluginLoader to register
     * @throws IllegalArgumentException Thrown when the given Class is not a
     * valid PluginLoader
     */
    @Throws(IllegalArgumentException::class)
    override fun registerInterface(loader: Class<out PluginLoader>) {
        val instance: PluginLoader
        if (PluginLoader::class.java.isAssignableFrom(loader)) {
            val constructor: Constructor<out PluginLoader>
            try {
                constructor = loader.getConstructor(Server::class.java)
                instance = constructor.newInstance(server)
            } catch (ex: NoSuchMethodException) {
                val className = loader.name
                throw IllegalArgumentException(
                    String.format(
                        "Class %s does not have a public %s(Server) constructor",
                        className,
                        className
                    ), ex
                )
            } catch (ex: Exception) {
                throw IllegalArgumentException(
                    String.format(
                        "Unexpected exception %s while attempting to construct a new instance of %s",
                        ex.javaClass.name,
                        loader.name
                    ), ex
                )
            }
        } else {
            throw IllegalArgumentException(
                String.format(
                    "Class %s does not implement interface PluginLoader",
                    loader.name
                )
            )
        }
        val patterns = instance.pluginFileFilters
        synchronized(this) {
            for (pattern in patterns) {
                fileAssociations[pattern] = instance
            }
        }
    }

    /**
     * Loads the plugins contained within the specified directory
     *
     * @param directory Directory to check for plugins
     * @return A list of all plugins loaded
     */
    override fun loadPlugins(directory: File): Array<Plugin> {
        if (!directory.isDirectory) throw IllegalArgumentException("Directory must be a directory")
        val result: MutableList<Plugin> = ArrayList()
        val filters: Set<Pattern> = fileAssociations.keys
        if (server.updateFolder != "") {
            updateDirectory = File(directory, server.updateFolder)
        }
        val plugins: MutableMap<String, File> = HashMap()
        val loadedPlugins: MutableSet<String> = HashSet()
        val pluginsProvided: MutableMap<String, String> = HashMap()
        val dependencies: MutableMap<String, MutableCollection<String>> = HashMap()
        val softDependencies: MutableMap<String, MutableCollection<String>> = HashMap()

        // This is where it figures out all possible plugins
        for (file in directory.listFiles()) {
            var loader: PluginLoader? = null
            for (filter in filters) {
                val match = filter.matcher(file.name)
                if (match.find()) {
                    loader = fileAssociations[filter]
                }
            }
            if (loader == null) continue
            var description: PluginDescriptionFile?
            try {
                description = loader.getPluginDescription(file)
                val name = description.name
                if (name.equals("bukkit", ignoreCase = true) || name.equals(
                        "minecraft",
                        ignoreCase = true
                    ) || name.equals("mojang", ignoreCase = true)
                ) {
                    server.logger.log(
                        Level.SEVERE,
                        "Could not load '" + file.path + "' in folder '" + directory.path + "': Restricted Name"
                    )
                    continue
                } else if (description.rawName.indexOf(' ') != -1) {
                    server.logger.log(
                        Level.SEVERE,
                        "Could not load '" + file.path + "' in folder '" + directory.path + "': uses the space-character (0x20) in its name"
                    )
                    continue
                }
            } catch (ex: InvalidDescriptionException) {
                server.logger.log(
                    Level.SEVERE,
                    "Could not load '" + file.path + "' in folder '" + directory.path + "'",
                    ex
                )
                continue
            }
            val replacedFile = plugins.put(description.name, file)
            if (replacedFile != null) {
                server.logger.severe(
                    String.format(
                        "Ambiguous plugin name `%s' for files `%s' and `%s' in `%s'",
                        description.name,
                        file.path,
                        replacedFile.path,
                        directory.path
                    )
                )
            }
            val removedProvided = pluginsProvided.remove(description.name)
            if (removedProvided != null) {
                server.logger.warning(
                    String.format(
                        "Ambiguous plugin name `%s'. It is also provided by `%s'",
                        description.name,
                        removedProvided
                    )
                )
            }
            for (provided in description.provides) {
                val pluginFile = plugins[provided]
                if (pluginFile != null) {
                    server.logger.warning(
                        String.format(
                            "`%s provides `%s' while this is also the name of `%s' in `%s'",
                            file.path,
                            provided,
                            pluginFile.path,
                            directory.path
                        )
                    )
                } else {
                    val replacedPlugin = pluginsProvided.put(provided, description.name)
                    if (replacedPlugin != null) {
                        server.logger.warning(
                            String.format(
                                "`%s' is provided by both `%s' and `%s'",
                                provided,
                                description.name,
                                replacedPlugin
                            )
                        )
                    }
                }
            }
            val softDependencySet: Collection<String> = description.softDepend
            if (!softDependencySet.isEmpty()) {
                if (softDependencies.containsKey(description.name)) {
                    // Duplicates do not matter, they will be removed together if applicable
                    softDependencies[description.name]!!.addAll(softDependencySet)
                } else {
                    softDependencies[description.name] = LinkedList(softDependencySet)
                }
                for (depend in softDependencySet) {
                    dependencyGraph.putEdge(description.name, depend)
                }
            }
            val dependencySet: Collection<String> = description.depend
            if (!dependencySet.isEmpty()) {
                dependencies[description.name] = LinkedList(dependencySet)
                for (depend in dependencySet) {
                    dependencyGraph.putEdge(description.name, depend)
                }
            }
            val loadBeforeSet: Collection<String> = description.loadBefore
            if (!loadBeforeSet.isEmpty()) {
                for (loadBeforeTarget in loadBeforeSet) {
                    if (softDependencies.containsKey(loadBeforeTarget)) {
                        softDependencies[loadBeforeTarget]!!.add(description.name)
                    } else {
                        // softDependencies is never iterated, so 'ghost' plugins aren't an issue
                        val shortSoftDependency: MutableCollection<String> = LinkedList()
                        shortSoftDependency.add(description.name)
                        softDependencies[loadBeforeTarget] = shortSoftDependency
                    }
                    dependencyGraph.putEdge(loadBeforeTarget, description.name)
                }
            }
        }
        while (!plugins.isEmpty()) {
            var missingDependency = true
            var pluginIterator: MutableIterator<Map.Entry<String, File>> = plugins.entries.iterator()
            while (pluginIterator.hasNext()) {
                val entry = pluginIterator.next()
                val plugin = entry.key
                if (dependencies.containsKey(plugin)) {
                    val dependencyIterator = dependencies[plugin]!!
                        .iterator()
                    while (dependencyIterator.hasNext()) {
                        val dependency = dependencyIterator.next()

                        // Dependency loaded
                        if (loadedPlugins.contains(dependency)) {
                            dependencyIterator.remove()

                            // We have a dependency not found
                        } else if (!plugins.containsKey(dependency) && !pluginsProvided.containsKey(dependency)) {
                            missingDependency = false
                            pluginIterator.remove()
                            softDependencies.remove(plugin)
                            dependencies.remove(plugin)
                            server.logger.log(
                                Level.SEVERE,
                                "Could not load '" + entry.value.path + "' in folder '" + directory.path + "'",
                                UnknownDependencyException("Unknown dependency $dependency. Please download and install $dependency to run this plugin.")
                            )
                            break
                        }
                    }
                    if (dependencies.containsKey(plugin) && dependencies[plugin]!!.isEmpty()) {
                        dependencies.remove(plugin)
                    }
                }
                if (softDependencies.containsKey(plugin)) {
                    val softDependencyIterator = softDependencies[plugin]!!
                        .iterator()
                    while (softDependencyIterator.hasNext()) {
                        val softDependency = softDependencyIterator.next()

                        // Soft depend is no longer around
                        if (!plugins.containsKey(softDependency) && !pluginsProvided.containsKey(softDependency)) {
                            softDependencyIterator.remove()
                        }
                    }
                    if (softDependencies[plugin]!!.isEmpty()) {
                        softDependencies.remove(plugin)
                    }
                }
                if (!(dependencies.containsKey(plugin) || softDependencies.containsKey(plugin)) && plugins.containsKey(
                        plugin
                    )
                ) {
                    // We're clear to load, no more soft or hard dependencies left
                    val file = plugins[plugin]
                    pluginIterator.remove()
                    missingDependency = false
                    try {
                        val loadedPlugin = loadPlugin(file!!)
                        if (loadedPlugin != null) {
                            result.add(loadedPlugin)
                            loadedPlugins.add(loadedPlugin.name)
                            loadedPlugins.addAll(loadedPlugin.description.provides)
                        } else {
                            server.logger.log(
                                Level.SEVERE,
                                "Could not load '" + file.path + "' in folder '" + directory.path + "'"
                            )
                        }
                        continue
                    } catch (ex: InvalidPluginException) {
                        server.logger.log(
                            Level.SEVERE,
                            "Could not load '" + file!!.path + "' in folder '" + directory.path + "'",
                            ex
                        )
                    }
                }
            }
            if (missingDependency) {
                // We now iterate over plugins until something loads
                // This loop will ignore soft dependencies
                pluginIterator = plugins.entries.iterator()
                while (pluginIterator.hasNext()) {
                    val entry = pluginIterator.next()
                    val plugin = entry.key
                    if (!dependencies.containsKey(plugin)) {
                        softDependencies.remove(plugin)
                        missingDependency = false
                        val file = entry.value
                        pluginIterator.remove()
                        try {
                            val loadedPlugin = loadPlugin(file)
                            if (loadedPlugin != null) {
                                result.add(loadedPlugin)
                                loadedPlugins.add(loadedPlugin.name)
                                loadedPlugins.addAll(loadedPlugin.description.provides)
                            } else {
                                server.logger.log(
                                    Level.SEVERE,
                                    "Could not load '" + file.path + "' in folder '" + directory.path + "'"
                                )
                            }
                            break
                        } catch (ex: InvalidPluginException) {
                            server.logger.log(
                                Level.SEVERE,
                                "Could not load '" + file.path + "' in folder '" + directory.path + "'",
                                ex
                            )
                        }
                    }
                }
                // We have no plugins left without a depend
                if (missingDependency) {
                    softDependencies.clear()
                    dependencies.clear()
                    val failedPluginIterator = plugins.values.iterator()
                    while (failedPluginIterator.hasNext()) {
                        val file = failedPluginIterator.next()
                        failedPluginIterator.remove()
                        server.logger.log(
                            Level.SEVERE,
                            "Could not load '" + file.path + "' in folder '" + directory.path + "': circular dependency detected"
                        )
                    }
                }
            }
        }
        TimingsCommand.timingStart = System.nanoTime() // Spigot
        return result.toTypedArray()
    }

    /**
     * Loads the plugin in the specified file
     *
     *
     * File must be valid according to the current enabled Plugin interfaces
     *
     * @param file File containing the plugin to load
     * @return The Plugin loaded, or null if it was invalid
     * @throws InvalidPluginException Thrown when the specified file is not a
     * valid plugin
     * @throws UnknownDependencyException If a required dependency could not
     * be found
     */
    @Synchronized
    @Throws(InvalidPluginException::class, UnknownDependencyException::class)
    override fun loadPlugin(file: File): Plugin? {
        checkUpdate(file)
        val filters: Set<Pattern> = fileAssociations.keys
        var result: Plugin? = null
        for (filter in filters) {
            val name = file.name
            val match = filter.matcher(name)
            if (match.find()) {
                val loader = fileAssociations[filter]
                result = loader!!.loadPlugin(file)
            }
        }
        if (result != null) {
            plugins.add(result)
            lookupNames[result.description.name] = result
            for (provided in result.description.provides) {
                lookupNames.putIfAbsent(provided, result)
            }
        }
        return result
    }

    private fun checkUpdate(file: File) {
        if (updateDirectory == null || !updateDirectory!!.isDirectory) {
            return
        }
        val updateFile = File(updateDirectory, file.name)
        if (updateFile.isFile && FileUtil.copy(updateFile, file)) {
            updateFile.delete()
        }
    }

    /**
     * Checks if the given plugin is loaded and returns it when applicable
     *
     *
     * Please note that the name of the plugin is case-sensitive
     *
     * @param name Name of the plugin to check
     * @return Plugin if it exists, otherwise null
     */
    @Synchronized
    override fun getPlugin(name: String): Plugin? {
        return lookupNames[name.replace(' ', '_')]
    }

    @Synchronized
    override fun getPlugins(): Array<Plugin> {
        return plugins.toTypedArray()
    }

    /**
     * Checks if the given plugin is enabled or not
     *
     *
     * Please note that the name of the plugin is case-sensitive.
     *
     * @param name Name of the plugin to check
     * @return true if the plugin is enabled, otherwise false
     */
    override fun isPluginEnabled(name: String): Boolean {
        val plugin = getPlugin(name)
        return isPluginEnabled(plugin)
    }

    /**
     * Checks if the given plugin is enabled or not
     *
     * @param plugin Plugin to check
     * @return true if the plugin is enabled, otherwise false
     */
    override fun isPluginEnabled(plugin: Plugin?): Boolean {
        return if (plugin != null && plugins.contains(plugin)) {
            plugin.isEnabled
        } else {
            false
        }
    }

    override fun enablePlugin(plugin: Plugin) {
        if (!plugin.isEnabled) {
            val pluginCommands = PluginCommandYamlParser.parse(plugin)
            if (pluginCommands.isNotEmpty()) {
                commandMap.registerAll(plugin.description.name, pluginCommands)
            }
            try {
                plugin.pluginLoader.enablePlugin(plugin)
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Error occurred (in the plugin loader) while enabling " + plugin.description.fullName + " (Is it up to date?)",
                    ex
                )
            }
            HandlerList.bakeAll()
        }
    }

    override fun disablePlugins() {
        val plugins = getPlugins()
        for (i in plugins.indices.reversed()) {
            disablePlugin(plugins[i])
        }
    }

    override fun disablePlugin(plugin: Plugin) {
        if (plugin.isEnabled) {
            try {
                plugin.pluginLoader.disablePlugin(plugin)
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Error occurred (in the plugin loader) while disabling " + plugin.description.fullName + " (Is it up to date?)",
                    ex
                )
            }
            try {
                server.scheduler.cancelTasks(plugin)
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Error occurred (in the plugin loader) while cancelling tasks for " + plugin.description.fullName + " (Is it up to date?)",
                    ex
                )
            }
            try {
                server.servicesManager.unregisterAll(plugin)
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Error occurred (in the plugin loader) while unregistering services for " + plugin.description.fullName + " (Is it up to date?)",
                    ex
                )
            }
            try {
                HandlerList.unregisterAll(plugin)
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Error occurred (in the plugin loader) while unregistering events for " + plugin.description.fullName + " (Is it up to date?)",
                    ex
                )
            }
            try {
                server.messenger.unregisterIncomingPluginChannel(plugin)
                server.messenger.unregisterOutgoingPluginChannel(plugin)
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Error occurred (in the plugin loader) while unregistering plugin channels for " + plugin.description.fullName + " (Is it up to date?)",
                    ex
                )
            }
            try {
                for (world in server.worlds) {
                    world.removePluginChunkTickets(plugin)
                }
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Error occurred (in the plugin loader) while removing chunk tickets for " + plugin.description.fullName + " (Is it up to date?)",
                    ex
                )
            }
        }
    }

    override fun clearPlugins() {
        synchronized(this) {
            disablePlugins()
            plugins.clear()
            lookupNames.clear()
            dependencyGraph = GraphBuilder.directed().build()
            HandlerList.unregisterAll()
            fileAssociations.clear()
            permissions.clear()
            defaultPerms[true]!!.clear()
            defaultPerms[false]!!.clear()
        }
    }

    /**
     * Calls an event with the given details.
     *
     * @param event Event details
     */
    override fun callEvent(event: Event) {
        if (event.isAsynchronous) {
            check(!Thread.holdsLock(this)) { event.eventName + " cannot be triggered asynchronously from inside synchronized code." }
            check(!server.isPrimaryThread) { event.eventName + " cannot be triggered asynchronously from primary server thread." }
        } else {
            check(server.isPrimaryThread) { event.eventName + " cannot be triggered asynchronously from another thread." }
        }
        fireEvent(event)
    }

    private fun fireEvent(event: Event) {
        val handlers = event.handlers
        val listeners = handlers.registeredListeners
        for (registration in listeners) {
            if (!registration.plugin.isEnabled) {
                continue
            }
            try {
                registration.callEvent(event)
            } catch (ex: AuthorNagException) {
                val plugin = registration.plugin
                if (plugin.isNaggable) {
                    plugin.isNaggable = false
                    server.logger.log(
                        Level.SEVERE, String.format(
                            "Nag author(s): '%s' of '%s' about the following: %s",
                            plugin.description.authors,
                            plugin.description.fullName,
                            ex.message
                        )
                    )
                }
            } catch (ex: Throwable) {
                server.logger.log(
                    Level.SEVERE,
                    "Could not pass event " + event.eventName + " to " + registration.plugin.description.fullName,
                    ex
                )
            }
        }
    }

    override fun registerEvents(listener: Listener, plugin: Plugin) {
        if (!plugin.isEnabled) {
            throw IllegalPluginAccessException("Plugin attempted to register $listener while not enabled")
        }
        for ((key, value) in plugin.pluginLoader.createRegisteredListeners(listener, plugin)) {
            getEventListeners(getRegistrationClass(key)).registerAll(value!!)
        }
    }

    override fun registerEvent(
        event: Class<out Event?>,
        listener: Listener,
        priority: EventPriority,
        executor: EventExecutor,
        plugin: Plugin
    ) {
        registerEvent(event, listener, priority, executor, plugin, false)
    }

    /**
     * Registers the given event to the specified listener using a directly
     * passed EventExecutor
     *
     * @param event Event class to register
     * @param listener PlayerListener to register
     * @param priority Priority of this event
     * @param executor EventExecutor to register
     * @param plugin Plugin to register
     * @param ignoreCancelled Do not call executor if event was already
     * cancelled
     */
    override fun registerEvent(
        event: Class<out Event?>,
        listener: Listener,
        priority: EventPriority,
        executor: EventExecutor,
        plugin: Plugin,
        ignoreCancelled: Boolean
    ) {
        if (!plugin.isEnabled) {
            throw IllegalPluginAccessException("Plugin attempted to register $event while not enabled")
        }
        if (useTimings) {
            getEventListeners(event).register(
                TimedRegisteredListener(
                    listener,
                    executor,
                    priority,
                    plugin,
                    ignoreCancelled
                )
            )
        } else {
            getEventListeners(event).register(RegisteredListener(listener, executor, priority, plugin, ignoreCancelled))
        }
    }

    private fun getEventListeners(type: Class<out Event?>): HandlerList {
        return try {
            val method = getRegistrationClass(type).getDeclaredMethod("getHandlerList")
            method.isAccessible = true
            method.invoke(null) as HandlerList
        } catch (e: Exception) {
            throw IllegalPluginAccessException(e.toString())
        }
    }

    private fun getRegistrationClass(clazz: Class<out Event?>): Class<out Event?> {
        return try {
            clazz.getDeclaredMethod("getHandlerList")
            clazz
        } catch (e: NoSuchMethodException) {
            if (clazz.superclass != null && clazz.superclass != Event::class.java
                && Event::class.java.isAssignableFrom(clazz.superclass)
            ) {
                getRegistrationClass(clazz.superclass.asSubclass(Event::class.java))
            } else {
                throw IllegalPluginAccessException("Unable to find handler list for event " + clazz.name + ". Static getHandlerList method required!")
            }
        }
    }

    override fun getPermission(name: String): Permission? {
        return permissions[name.toLowerCase(Locale.ENGLISH)]
    }

    override fun addPermission(perm: Permission) {
        addPermission(perm, true)
    }

    @Deprecated("")
    fun addPermission(perm: Permission, dirty: Boolean) {
        val name = perm.name.toLowerCase(Locale.ENGLISH)
        require(!permissions.containsKey(name)) { "The permission $name is already defined!" }
        permissions[name] = perm
        calculatePermissionDefault(perm, dirty)
    }

    override fun getDefaultPermissions(op: Boolean): Set<Permission> {
        return ImmutableSet.copyOf(defaultPerms[op])
    }

    override fun removePermission(perm: Permission) {
        removePermission(perm.name)
    }

    override fun removePermission(name: String) {
        permissions.remove(name.toLowerCase(Locale.ENGLISH))
    }

    override fun recalculatePermissionDefaults(perm: Permission) {
        if (permissions.containsKey(perm.name.toLowerCase(Locale.ENGLISH))) {
            defaultPerms[true]!!.remove(perm)
            defaultPerms[false]!!.remove(perm)
            calculatePermissionDefault(perm, true)
        }
    }

    private fun calculatePermissionDefault(perm: Permission, dirty: Boolean) {
        if (perm.default == PermissionDefault.OP || perm.default == PermissionDefault.TRUE) {
            defaultPerms[true]!!.add(perm)
            if (dirty) {
                dirtyPermissibles(true)
            }
        }
        if (perm.default == PermissionDefault.NOT_OP || perm.default == PermissionDefault.TRUE) {
            defaultPerms[false]!!.add(perm)
            if (dirty) {
                dirtyPermissibles(false)
            }
        }
    }

    @Deprecated("")
    fun dirtyPermissibles() {
        dirtyPermissibles(true)
        dirtyPermissibles(false)
    }

    private fun dirtyPermissibles(op: Boolean) {
        val permissibles = getDefaultPermSubscriptions(op)
        for (p in permissibles) {
            p.recalculatePermissions()
        }
    }

    override fun subscribeToPermission(permission: String, permissible: Permissible) {
        val name = permission.toLowerCase(Locale.ENGLISH)
        var map = permSubs[name]
        if (map == null) {
            map = WeakHashMap()
            permSubs[name] = map
        }
        map[permissible] = true
    }

    override fun unsubscribeFromPermission(permission: String, permissible: Permissible) {
        val name = permission.toLowerCase(Locale.ENGLISH)
        val map = permSubs[name]
        if (map != null) {
            map.remove(permissible)
            if (map.isEmpty()) {
                permSubs.remove(name)
            }
        }
    }

    override fun getPermissionSubscriptions(permission: String): Set<Permissible> {
        val name = permission.toLowerCase(Locale.ENGLISH)
        val map: Map<Permissible, Boolean>? = permSubs[name]
        return if (map == null) {
            ImmutableSet.of()
        } else {
            ImmutableSet.copyOf(map.keys)
        }
    }

    override fun subscribeToDefaultPerms(op: Boolean, permissible: Permissible) {
        var map = defSubs[op]
        if (map == null) {
            map = WeakHashMap()
            defSubs[op] = map
        }
        map[permissible] = true
    }

    override fun unsubscribeFromDefaultPerms(op: Boolean, permissible: Permissible) {
        val map = defSubs[op]
        if (map != null) {
            map.remove(permissible)
            if (map.isEmpty()) {
                defSubs.remove(op)
            }
        }
    }

    override fun getDefaultPermSubscriptions(op: Boolean): Set<Permissible> {
        val map: Map<Permissible, Boolean>? = defSubs[op]
        return if (map == null) {
            ImmutableSet.of()
        } else {
            ImmutableSet.copyOf(map.keys)
        }
    }

    override fun getPermissions(): Set<Permission> {
        return HashSet(permissions.values)
    }

    fun isTransitiveDepend(plugin: PluginDescriptionFile, depend: PluginDescriptionFile): Boolean {
        Preconditions.checkArgument(true, "plugin")
        Preconditions.checkArgument(true, "depend")
        if (dependencyGraph.nodes().contains(plugin.name)) {
            if (Graphs.reachableNodes(dependencyGraph, plugin.name).contains(depend.name)) {
                return true
            }
            for (provided in depend.provides) {
                if (Graphs.reachableNodes(dependencyGraph, plugin.name).contains(provided)) {
                    return true
                }
            }
        }
        return false
    }

    override fun useTimings(): Boolean {
        return useTimings
    }

    /**
     * Sets whether or not per event timing code should be used
     *
     * @param use True if per event timing code should be used
     */
    fun useTimings(use: Boolean) {
        useTimings = use
    }

    init {
        defaultPerms[true] = LinkedHashSet()
        defaultPerms[false] = LinkedHashSet()
    }
}