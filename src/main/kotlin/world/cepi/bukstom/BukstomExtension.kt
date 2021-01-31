package world.cepi.bukstom

import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension
import org.apache.commons.lang.Validate
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginLoadOrder
import java.io.File

val pluginFolder = File("./plugins")

class BukstomExtension : Extension() {

    companion object {
        val server = MinestomServer()
    }

    override fun initialize() {

        MinecraftServer.getCommandManager().register(BukkitCommand())

        Bukkit.setServer(server)
        server.loadPlugins()
        server.enablePlugins(PluginLoadOrder.STARTUP)
        server.enablePlugins(PluginLoadOrder.POSTWORLD) // TODO actually post world

        logger.info("[BukstomExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[BukstomExtension] has been disabled!")
    }

}
