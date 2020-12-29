package world.cepi.bukstom

import net.minestom.server.extensions.Extension
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.craftbukkit.CraftServer
import java.io.File

val pluginFolder = File("./plugins")

class BukstomExtension : Extension() {

    companion object {
        val server = CraftServer()
    }

    override fun initialize() {

        Bukkit.setServer(server)
        server.loadPlugins()

        logger.info("[BukstomExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[BukstomExtension] has been disabled!")
    }

}