package org.bukkit.craftbukkit.command

import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.SimpleCommandMap


class CraftCommandMap(server: Server) : SimpleCommandMap(server) {
    val knownCommands: Map<String, Command>
        get() = knownCommands
}
