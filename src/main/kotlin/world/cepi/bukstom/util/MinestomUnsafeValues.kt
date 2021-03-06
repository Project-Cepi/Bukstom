package world.cepi.bukstom.util

import io.papermc.paper.inventory.ItemRarity
import net.kyori.adventure.text.flattener.ComponentFlattener
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.SECTION_CHAR
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import net.minestom.server.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.UnsafeValues
import org.bukkit.advancement.Advancement
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import org.bukkit.plugin.PluginDescriptionFile
import java.util.logging.Level
import org.bukkit.plugin.InvalidPluginException

object MinestomUnsafeValues: UnsafeValues {

    private val SUPPORTED_API: List<String> = listOf("1.13", "1.14", "1.15", "1.16")

    override fun getTranslationKey(itemStack: ItemStack?): String {
        TODO("Not yet implemented")
    }

    override fun componentFlattener() = ComponentFlattener.basic()

    override fun plainComponentSerializer() = PlainComponentSerializer.plain()

    override fun gsonComponentSerializer() = GsonComponentSerializer.gson()

    override fun colorDownsamplingGsonComponentSerializer() = GsonComponentSerializer.colorDownsamplingGson()

    override fun legacyComponentSerializer(): LegacyComponentSerializer =
        LegacyComponentSerializer.legacy(SECTION_CHAR)

    override fun reportTimings() {
        TODO("Not yet implemented")
    }

    override fun toLegacy(material: Material?): Material {
        TODO("Not yet implemented")
    }

    override fun fromLegacy(material: Material?): Material {
        TODO("Not yet implemented")
    }

    override fun fromLegacy(material: MaterialData?): Material {
        TODO("Not yet implemented")
    }

    override fun fromLegacy(material: MaterialData?, itemPriority: Boolean): Material {
        TODO("Not yet implemented")
    }

    override fun fromLegacy(material: Material?, data: Byte): BlockData {
        TODO("Not yet implemented")
    }

    override fun getMaterial(material: String?, version: Int): Material {
        TODO("Not yet implemented")
    }

    override fun getDataVersion(): Int {
        TODO("Not yet implemented")
    }

    override fun modifyItemStack(stack: ItemStack?, arguments: String?): ItemStack {
        TODO("Not yet implemented")
    }

    // TODO this currently "pretends" we support all plugins
    override fun checkSupported(pdf: PluginDescriptionFile?) {

    }

    private fun isLegacy(pdf: PluginDescriptionFile): Boolean {
        return pdf.apiVersion == null
    }


    override fun processClass(pdf: PluginDescriptionFile, path: String, clazz: ByteArray): ByteArray? {
        return try {
            Commodore.convert(clazz, !isLegacy(pdf))
        } catch (ex: Exception) {
            Bukkit.getLogger().log(Level.SEVERE, "Fatal error trying to convert " + pdf.fullName + ":" + path, ex)
            null
        }

    }

    override fun loadAdvancement(key: NamespacedKey?, advancement: String?): Advancement {
        TODO("Not yet implemented")
    }

    override fun removeAdvancement(key: NamespacedKey?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTimingsServerName(): String {
        return MinecraftServer.getBrandName()
    }

    override fun isSupportedApiVersion(apiVersion: String?) = true

    override fun serializeItem(item: ItemStack?): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserializeItem(data: ByteArray?): ItemStack {
        TODO("Not yet implemented")
    }

    override fun getTranslationKey(mat: Material?): String {
        TODO("Not yet implemented")
    }

    override fun getTranslationKey(block: Block?): String {
        TODO("Not yet implemented")
    }

    override fun getTranslationKey(type: EntityType?): String {
        TODO("Not yet implemented")
    }

    override fun nextEntityId(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemRarity(material: Material?): ItemRarity {
        TODO("Not yet implemented")
    }

    override fun getItemStackRarity(itemStack: ItemStack?): ItemRarity {
        TODO("Not yet implemented")
    }

    override fun getProtocolVersion(): Int {
        TODO("Not yet implemented")
    }
}