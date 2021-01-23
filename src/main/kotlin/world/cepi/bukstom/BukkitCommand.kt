package world.cepi.bukstom

import net.minestom.server.command.CommandProcessor
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.command.MinestomCommandSender

class BukkitCommand: CommandProcessor {
    override fun getCommandName(): String {
        return "runbukkit"
    }

    override fun getAliases(): Array<String> {
        return arrayOf("rb")
    }

    override fun process(sender: CommandSender, command: String, args: Array<out String>): Boolean {
        return Bukkit.getServer().dispatchCommand(MinestomCommandSender(sender), args.joinToString(" "))
    }

    override fun hasAccess(player: Player): Boolean {
        return true
    }


}