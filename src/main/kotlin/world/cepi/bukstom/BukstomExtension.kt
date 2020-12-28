package world.cepi.bukstom

import net.minestom.server.extensions.Extension;

class BukstomExtension : Extension() {

    override fun initialize() {
        logger.info("[BukstomExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[BukstomExtension] has been disabled!")
    }

}