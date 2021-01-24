package world.cepi.bukstom

import net.minestom.server.MinecraftServer
import net.minestom.server.chat.ColoredText
import net.minestom.server.extras.MojangAuth
import org.bukkit.*
import org.bukkit.advancement.Advancement
import org.bukkit.block.data.BlockData
import org.bukkit.boss.*
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.PluginCommand
import world.cepi.bukstom.command.MinestomCommandMap
import world.cepi.bukstom.entity.MinestomPlayer
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.generator.ChunkGenerator
import org.bukkit.help.HelpMap
import org.bukkit.inventory.*
import org.bukkit.loot.LootTable
import org.bukkit.map.MapView
import org.bukkit.permissions.Permission
import org.bukkit.plugin.*
import org.bukkit.plugin.java.JavaPluginLoader
import org.bukkit.plugin.messaging.Messenger
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scoreboard.ScoreboardManager
import org.bukkit.util.CachedServerIcon
import world.cepi.bukstom.util.MinestomUnsafeValues
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import java.util.function.Consumer
import java.util.logging.Level
import java.util.logging.Logger


class MinestomServer: Server {
    val commandMap = MinestomCommandMap(this)
    private val pluginManager = MinestomPluginLoader(this, commandMap)
    private val logger = Logger.getLogger("Minecraft")
    private val helpMap = MinestomHelpMap(this)

    override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getName(): String {
        return MinecraftServer.getBrandName()
    }

    override fun getVersion(): String {
        return MinecraftServer.VERSION_NAME
    }

    override fun getBukkitVersion(): String {
        return MinecraftServer.VERSION_NAME
    }

    override fun getOnlinePlayers(): MutableCollection<out Player> {
        return Collections.unmodifiableList(MinecraftServer.getConnectionManager().onlinePlayers.map { MinestomPlayer(it) })
    }

    override fun getMaxPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun getPort(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewDistance(): Int {
        return MinecraftServer.getChunkViewDistance()
    }

    override fun getIp(): String {
        TODO("Not yet implemented")
    }

    override fun getWorldType(): String {
        TODO("Not yet implemented")
    }

    override fun getGenerateStructures(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMaxWorldSize(): Int {
        TODO("Not yet implemented")
    }

    override fun getAllowEnd(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllowNether(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasWhitelist(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setWhitelist(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getWhitelistedPlayers(): MutableSet<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun reloadWhitelist() {
        TODO("Not yet implemented")
    }

    override fun broadcastMessage(message: String): Int {
        MinecraftServer.getConnectionManager().broadcastMessage(ColoredText.of(message))
        return 1 // TODO actually return a useful value
    }

    override fun getUpdateFolder(): String {
        return "update" // TODO add to configuration
    }

    override fun getUpdateFolderFile(): File {
        return File("./plugins/update") // TODO add to configuration
    }

    override fun getConnectionThrottle(): Long {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAnimalSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerMonsterSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterAmbientSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAmbientSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getPlayer(name: String): Player? {
        TODO("Not yet implemented")
    }

    override fun getPlayer(id: UUID): Player? {
        return MinecraftServer.getConnectionManager().getPlayer(id)?.let { MinestomPlayer(it) }
    }

    override fun getPlayerExact(name: String): Player? {
        TODO("Not yet implemented")
    }

    override fun matchPlayer(name: String): MutableList<Player> {
        TODO("Not yet implemented")
    }

    override fun getPluginManager(): PluginManager {
        return pluginManager
    }

    override fun getScheduler(): BukkitScheduler {
        TODO("Not yet implemented")
    }

    override fun getServicesManager(): ServicesManager {
        TODO("Not yet implemented")
    }

    override fun getWorlds(): MutableList<World> {
        TODO("Not yet implemented")
    }

    override fun createWorld(creator: WorldCreator): World? {
        TODO("Not yet implemented")
    }

    override fun unloadWorld(name: String, save: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadWorld(world: World, save: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorld(name: String): World? {
        TODO("Not yet implemented")
    }

    override fun getWorld(uid: UUID): World? {
        TODO("Not yet implemented")
    }

    override fun getMap(id: Int): MapView? {
        TODO("Not yet implemented")
    }

    override fun createMap(world: World): MapView {
        TODO("Not yet implemented")
    }

    override fun createExplorerMap(world: World, location: Location, structureType: StructureType): ItemStack {
        TODO("Not yet implemented")
    }

    override fun createExplorerMap(
        world: World,
        location: Location,
        structureType: StructureType,
        radius: Int,
        findUnexplored: Boolean
    ): ItemStack {
        TODO("Not yet implemented")
    }

    override fun reload() {
        TODO("Not yet implemented")
    }

    override fun reloadData() {
        TODO("Not yet implemented")
    }

    override fun getLogger(): Logger {
        return logger
    }

    override fun getPluginCommand(name: String): PluginCommand? {
        val command = commandMap.getCommand(name)

        return if (command is PluginCommand) {
            command
        } else {
            null
        }

    }

    override fun savePlayers() {
        TODO("Not yet implemented")
    }

    override fun dispatchCommand(sender: CommandSender, commandLine: String): Boolean {
        if (commandMap.dispatch(sender, commandLine)) {
            return true
        }

        if (sender is Player) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.")
        } else {
            sender.sendMessage("Unknown command. Type \"help\" for help.")
        }

        return false

    }

    override fun addRecipe(recipe: Recipe?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRecipesFor(result: ItemStack): MutableList<Recipe> {
        TODO("Not yet implemented")
    }

    override fun getRecipe(recipeKey: NamespacedKey): Recipe? {
        TODO("Not yet implemented")
    }

    override fun recipeIterator(): MutableIterator<Recipe> {
        TODO("Not yet implemented")
    }

    override fun clearRecipes() {
        TODO("Not yet implemented")
    }

    override fun resetRecipes() {
        TODO("Not yet implemented")
    }

    override fun removeRecipe(key: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCommandAliases(): MutableMap<String, Array<String>> {
        // TODO commands configuration
//        val section: ConfigurationSection = commandsConfiguration.getConfigurationSection("aliases")
//        val result: MutableMap<String, Array<String?>> = LinkedHashMap()
//
//        for (key in section.getKeys(false)) {
//            val commands: List<String?> = if (section.isList(key)) {
//                section.getStringList(key)
//            } else {
//                ImmutableList.of(section.getString(key))
//            }
//            result[key] = commands.toTypedArray()
//        }
//
//        return result
        return mutableMapOf()
    }

    override fun getSpawnRadius(): Int {
        TODO("Not yet implemented")
    }

    override fun setSpawnRadius(value: Int) {
        TODO("Not yet implemented")
    }

    override fun getOnlineMode(): Boolean {
        return MojangAuth.isEnabled()
    }

    override fun getAllowFlight(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isHardcore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun shutdown() {
        MinecraftServer.stopCleanly()
    }

    override fun broadcast(message: String, permission: String): Int {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayer(name: String): OfflinePlayer {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayer(id: UUID): OfflinePlayer {
        TODO("Not yet implemented")
    }

    override fun getIPBans(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun banIP(address: String) {
        TODO("Not yet implemented")
    }

    override fun unbanIP(address: String) {
        TODO("Not yet implemented")
    }

    override fun getBannedPlayers(): MutableSet<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getBanList(type: BanList.Type): BanList {
        TODO("Not yet implemented")
    }

    override fun getOperators(): MutableSet<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getDefaultGameMode(): GameMode {
        TODO("Not yet implemented")
    }

    override fun setDefaultGameMode(mode: GameMode) {
        TODO("Not yet implemented")
    }

    override fun getConsoleSender(): ConsoleCommandSender {
        TODO("Not yet implemented")
    }

    override fun getWorldContainer(): File {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayers(): Array<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getMessenger(): Messenger {
        TODO("Not yet implemented")
    }

    override fun getHelpMap(): HelpMap {
        return helpMap
    }

    override fun createInventory(owner: InventoryHolder?, type: InventoryType): Inventory {
        TODO("Not yet implemented")
    }

    override fun createInventory(owner: InventoryHolder?, type: InventoryType, title: String): Inventory {
        TODO("Not yet implemented")
    }

    override fun createInventory(owner: InventoryHolder?, size: Int): Inventory {
        TODO("Not yet implemented")
    }

    override fun createInventory(owner: InventoryHolder?, size: Int, title: String): Inventory {
        TODO("Not yet implemented")
    }

    override fun createMerchant(title: String?): Merchant {
        TODO("Not yet implemented")
    }

    override fun getMonsterSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getWaterAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getWaterAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun isPrimaryThread(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMotd(): String {
        TODO("Not yet implemented")
    }

    override fun getShutdownMessage(): String? {
        TODO("Not yet implemented")
    }

    override fun getWarningState(): Warning.WarningState {
        TODO("Not yet implemented")
    }

    override fun getItemFactory(): ItemFactory {
        TODO("Not yet implemented")
    }

    override fun getScoreboardManager(): ScoreboardManager? {
        TODO("Not yet implemented")
    }

    override fun getServerIcon(): CachedServerIcon? {
        TODO("Not yet implemented")
    }

    override fun loadServerIcon(file: File): CachedServerIcon {
        TODO("Not yet implemented")
    }

    override fun loadServerIcon(image: BufferedImage): CachedServerIcon {
        TODO("Not yet implemented")
    }

    override fun setIdleTimeout(threshold: Int) {
        TODO("Not yet implemented")
    }

    override fun getIdleTimeout(): Int {
        TODO("Not yet implemented")
    }

    override fun createChunkData(world: World): ChunkGenerator.ChunkData {
        TODO("Not yet implemented")
    }

    override fun createBossBar(title: String?, color: BarColor, style: BarStyle, vararg flags: BarFlag?): BossBar {
        TODO("Not yet implemented")
    }

    override fun createBossBar(
        key: NamespacedKey,
        title: String?,
        color: BarColor,
        style: BarStyle,
        vararg flags: BarFlag?
    ): KeyedBossBar {
        TODO("Not yet implemented")
    }

    override fun getBossBars(): MutableIterator<KeyedBossBar> {
        TODO("Not yet implemented")
    }

    override fun getBossBar(key: NamespacedKey): KeyedBossBar? {
        TODO("Not yet implemented")
    }

    override fun removeBossBar(key: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEntity(uuid: UUID): Entity? {
        TODO("Not yet implemented")
    }

    override fun getAdvancement(key: NamespacedKey): Advancement? {
        TODO("Not yet implemented")
    }

    override fun advancementIterator(): MutableIterator<Advancement> {
        TODO("Not yet implemented")
    }

    override fun createBlockData(material: Material): BlockData {
        TODO("Not yet implemented")
    }

    override fun createBlockData(material: Material, consumer: Consumer<BlockData>?): BlockData {
        TODO("Not yet implemented")
    }

    override fun createBlockData(data: String): BlockData {
        TODO("Not yet implemented")
    }

    override fun createBlockData(material: Material?, data: String?): BlockData {
        TODO("Not yet implemented")
    }

    override fun <T : Keyed?> getTag(registry: String, tag: NamespacedKey, clazz: Class<T>): Tag<T>? {
        TODO("Not yet implemented")
    }

    override fun <T : Keyed?> getTags(registry: String, clazz: Class<T>): MutableIterable<Tag<T>> {
        TODO("Not yet implemented")
    }

    override fun getLootTable(key: NamespacedKey): LootTable? {
        TODO("Not yet implemented")
    }

    override fun selectEntities(sender: CommandSender, selector: String): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun getUnsafe(): UnsafeValues {
        return MinestomUnsafeValues
    }

    override fun spigot(): Server.Spigot {
        TODO("Not yet implemented")
    }

    fun loadPlugins() {
        pluginManager.registerInterface(JavaPluginLoader::class.java)
        pluginFolder.mkdirs()

        pluginManager.loadPlugins(pluginFolder).forEach {
            try {
                it.logger.info("Loading ${it.description.fullName}")
                it.onLoad()
            } catch (ex: Exception) {
                Logger.getLogger(MinestomServer::class.java.name).log(
                    Level.SEVERE,
                    ex.message.toString() + " initializing " + it.description
                        .fullName + " (Is it up to date?)",
                    ex
                )
            }
        }
    }

    fun enablePlugins(type: PluginLoadOrder) {
        if (type == PluginLoadOrder.STARTUP) {
            helpMap.clear()
            // TODO
//            helpMap.initializeGeneralTopics()
        }
        val plugins = pluginManager.plugins
        for (plugin in plugins) {
            if (!plugin.isEnabled && plugin.description.load === type) {
                enablePlugin(plugin)
            }
        }
        if (type == PluginLoadOrder.POSTWORLD) {
            // TODO
            commandMap.setFallbackCommands()
//            setVanillaCommands()
            commandMap.registerServerAliases()
            // TODO fix validate
//            DefaultPermissions.registerCorePermissions()
//            CraftDefaultPermissions.registerCorePermissions()
//            loadCustomPermissions()
//            helpMap.initializeCommands()
//            syncCommands()
        }
    }

    fun disablePlugins() {
        pluginManager.disablePlugins()
    }
    private fun enablePlugin(plugin: Plugin) {
        try {
            val perms: List<Permission> = plugin.description.permissions
            for (perm in perms) {
                try {
                    pluginManager.addPermission(perm, false)
                } catch (ex: IllegalArgumentException) {
                    getLogger().log(
                        Level.WARNING,
                        "Plugin " + plugin.description.fullName + " tried to register permission '" + perm.name + "' but it's already registered",
                        ex
                    )
                }
            }
            pluginManager.dirtyPermissibles()
            pluginManager.enablePlugin(plugin)
        } catch (ex: Throwable) {
            Logger.getLogger(MinestomServer::class.java.name)
                .log(Level.SEVERE, ex.message + " loading " + plugin.description.fullName + " (Is it up to date?)", ex)
        }
    }


}