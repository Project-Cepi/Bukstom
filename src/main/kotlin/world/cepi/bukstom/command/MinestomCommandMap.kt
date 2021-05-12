package world.cepi.bukstom.command

import org.bukkit.Server
import org.bukkit.command.*

open class MinestomCommandMap(server: Server) : SimpleCommandMap(server) {
    override fun getKnownCommands(): Map<String, Command> {
        return knownCommands
    }
}