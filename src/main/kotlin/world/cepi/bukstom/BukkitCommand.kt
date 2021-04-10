package world.cepi.bukstom

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.SimpleCommand
import org.bukkit.Bukkit
import world.cepi.bukstom.command.MinestomCommandSender

object BukkitCommand: SimpleCommand("runbukkit", "rb") {

    override fun process(sender: CommandSender, command: String, args: Array<out String>): Boolean {
        return Bukkit.getServer().dispatchCommand(MinestomCommandSender(sender), args.joinToString(" "))
    }

    override fun hasAccess(sender: CommandSender, commandString: String?): Boolean {
        return true
    }


}