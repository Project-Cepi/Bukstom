package world.cepi.bukstom

import com.destroystokyo.paper.HeightmapType
import io.papermc.paper.world.MoonPhase
import net.minestom.server.instance.Instance
import org.bukkit.*
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.boss.DragonBattle
import org.bukkit.entity.*
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox
import org.bukkit.util.Consumer
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import java.io.File
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Predicate

class MinestomWorld(val instance: Instance) : World {
    override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun setMetadata(metadataKey: String, newMetadataValue: MetadataValue) {
        TODO("Not yet implemented")
    }

    override fun getMetadata(metadataKey: String): MutableList<MetadataValue> {
        TODO("Not yet implemented")
    }

    override fun hasMetadata(metadataKey: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeMetadata(metadataKey: String, owningPlugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: Sound, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: String, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: Sound, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: String, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun getKey(): NamespacedKey {
        TODO("Not yet implemented")
    }

    override fun getEntityCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getTileEntityCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getTickableTileEntityCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getChunkCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getPlayerCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getMoonPhase(): MoonPhase {
        TODO("Not yet implemented")
    }

    override fun getBlockAt(x: Int, y: Int, z: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getBlockAt(location: Location): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(x: Int, z: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(location: Location): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(x: Int, z: Int, heightmap: HeightmapType): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(x: Int, z: Int, heightMap: HeightMap): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(location: Location, heightMap: HeightMap): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(x: Int, z: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(location: Location): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(x: Int, z: Int, heightMap: HeightMap): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(location: Location, heightMap: HeightMap): Block {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(x: Int, z: Int): Chunk {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(location: Location): Chunk {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(block: Block): Chunk {
        TODO("Not yet implemented")
    }

    override fun isChunkGenerated(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getChunkAtAsync(x: Int, z: Int, gen: Boolean, urgent: Boolean): CompletableFuture<Chunk> {
        TODO("Not yet implemented")
    }

    override fun isChunkLoaded(chunk: Chunk): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkLoaded(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLoadedChunks(): Array<Chunk> {
        TODO("Not yet implemented")
    }

    override fun loadChunk(chunk: Chunk) {
        instance.loadChunk(chunk.x, chunk.z)
    }

    override fun loadChunk(x: Int, z: Int) {
        instance.loadChunk(x, z)
    }

    override fun loadChunk(x: Int, z: Int, generate: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkInUse(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunk(chunk: Chunk): Boolean {
        instance.unloadChunk(chunk.x, chunk.z)
        return true
    }

    override fun unloadChunk(x: Int, z: Int): Boolean {
        instance.unloadChunk(x, z)
        return true
    }

    override fun unloadChunk(x: Int, z: Int, save: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunkRequest(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun regenerateChunk(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun refreshChunk(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkForceLoaded(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun setChunkForceLoaded(x: Int, z: Int, forced: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getForceLoadedChunks(): MutableCollection<Chunk> {
        TODO("Not yet implemented")
    }

    override fun addPluginChunkTicket(x: Int, z: Int, plugin: Plugin): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePluginChunkTicket(x: Int, z: Int, plugin: Plugin): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePluginChunkTickets(plugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun getPluginChunkTickets(x: Int, z: Int): MutableCollection<Plugin> {
        TODO("Not yet implemented")
    }

    override fun getPluginChunkTickets(): MutableMap<Plugin, MutableCollection<Chunk>> {
        TODO("Not yet implemented")
    }

    override fun dropItem(location: Location, item: ItemStack): Item {
        TODO("Not yet implemented")
    }

    override fun dropItem(location: Location, item: ItemStack, function: Consumer<Item>?): Item {
        TODO("Not yet implemented")
    }

    override fun dropItemNaturally(location: Location, item: ItemStack): Item {
        TODO("Not yet implemented")
    }

    override fun dropItemNaturally(location: Location, item: ItemStack, function: Consumer<Item>?): Item {
        TODO("Not yet implemented")
    }

    override fun spawnArrow(location: Location, direction: Vector, speed: Float, spread: Float): Arrow {
        TODO("Not yet implemented")
    }

    override fun <T : AbstractArrow?> spawnArrow(
        location: Location,
        direction: Vector,
        speed: Float,
        spread: Float,
        clazz: Class<T>
    ): T {
        TODO("Not yet implemented")
    }

    override fun generateTree(location: Location, type: TreeType): Boolean {
        TODO("Not yet implemented")
    }

    override fun generateTree(loc: Location, type: TreeType, delegate: BlockChangeDelegate): Boolean {
        TODO("Not yet implemented")
    }

    override fun spawnEntity(loc: Location, type: EntityType): Entity {
        TODO("Not yet implemented")
    }

    override fun strikeLightning(loc: Location): LightningStrike {
        TODO("Not yet implemented")
    }

    override fun strikeLightningEffect(loc: Location): LightningStrike {
        TODO("Not yet implemented")
    }

    override fun getEntities(): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun getLivingEntities(): MutableList<LivingEntity> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> getEntitiesByClass(vararg classes: Class<T>): MutableCollection<T> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> getEntitiesByClass(cls: Class<T>): MutableCollection<T> {
        TODO("Not yet implemented")
    }

    override fun getEntitiesByClasses(vararg classes: Class<*>): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getPlayers(): MutableList<Player> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(location: Location, x: Double, y: Double, z: Double): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(
        location: Location,
        x: Double,
        y: Double,
        z: Double,
        filter: Predicate<Entity>?
    ): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(boundingBox: BoundingBox): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(boundingBox: BoundingBox, filter: Predicate<Entity>?): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getEntity(uuid: UUID): Entity? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(start: Location, direction: Vector, maxDistance: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        raySize: Double
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        filter: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        raySize: Double,
        filter: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(start: Location, direction: Vector, maxDistance: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode,
        ignorePassableBlocks: Boolean
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTrace(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode,
        ignorePassableBlocks: Boolean,
        raySize: Double,
        filter: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getUID(): UUID {
        TODO("Not yet implemented")
    }

    override fun getSpawnLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun setSpawnLocation(location: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSpawnLocation(x: Int, y: Int, z: Int, angle: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSpawnLocation(x: Int, y: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTime(): Long {
        TODO("Not yet implemented")
    }

    override fun setTime(time: Long) {
        TODO("Not yet implemented")
    }

    override fun getFullTime(): Long {
        TODO("Not yet implemented")
    }

    override fun setFullTime(time: Long) {
        TODO("Not yet implemented")
    }

    override fun isDayTime(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getGameTime(): Long {
        TODO("Not yet implemented")
    }

    override fun hasStorm(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setStorm(hasStorm: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getWeatherDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun setWeatherDuration(duration: Int) {
        TODO("Not yet implemented")
    }

    override fun isThundering(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setThundering(thundering: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getThunderDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun setThunderDuration(duration: Int) {
        TODO("Not yet implemented")
    }

    override fun isClearWeather(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setClearWeatherDuration(duration: Int) {
        TODO("Not yet implemented")
    }

    override fun getClearWeatherDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun createExplosion(x: Double, y: Double, z: Double, power: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(x: Double, y: Double, z: Double, power: Float, setFire: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        x: Double,
        y: Double,
        z: Double,
        power: Float,
        setFire: Boolean,
        breakBlocks: Boolean
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        x: Double,
        y: Double,
        z: Double,
        power: Float,
        setFire: Boolean,
        breakBlocks: Boolean,
        source: Entity?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(loc: Location, power: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(loc: Location, power: Float, setFire: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        source: Entity?,
        loc: Location,
        power: Float,
        setFire: Boolean,
        breakBlocks: Boolean
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(loc: Location, power: Float, setFire: Boolean, breakBlocks: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        loc: Location,
        power: Float,
        setFire: Boolean,
        breakBlocks: Boolean,
        source: Entity?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEnvironment(): World.Environment {
        TODO("Not yet implemented")
    }

    override fun getSeed(): Long {
        TODO("Not yet implemented")
    }

    override fun getPVP(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setPVP(pvp: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getGenerator(): ChunkGenerator? {
        TODO("Not yet implemented")
    }

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun getPopulators(): MutableList<BlockPopulator> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> spawn(location: Location, clazz: Class<T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> spawn(
        location: Location,
        clazz: Class<T>,
        function: Consumer<T>?,
        reason: CreatureSpawnEvent.SpawnReason
    ): T {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(location: Location, data: MaterialData): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(location: Location, data: BlockData): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(location: Location, material: Material, data: Byte): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun playEffect(location: Location, effect: Effect, data: Int) {
        TODO("Not yet implemented")
    }

    override fun playEffect(location: Location, effect: Effect, data: Int, radius: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?, radius: Int) {
        TODO("Not yet implemented")
    }

    override fun getEmptyChunkSnapshot(
        x: Int,
        z: Int,
        includeBiome: Boolean,
        includeBiomeTemp: Boolean
    ): ChunkSnapshot {
        TODO("Not yet implemented")
    }

    override fun setSpawnFlags(allowMonsters: Boolean, allowAnimals: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getAllowAnimals(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllowMonsters(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBiome(x: Int, z: Int): Biome {
        TODO("Not yet implemented")
    }

    override fun getBiome(x: Int, y: Int, z: Int): Biome {
        TODO("Not yet implemented")
    }

    override fun setBiome(x: Int, z: Int, bio: Biome) {
        TODO("Not yet implemented")
    }

    override fun setBiome(x: Int, y: Int, z: Int, bio: Biome) {
        TODO("Not yet implemented")
    }

    override fun getTemperature(x: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getTemperature(x: Int, y: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(x: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(x: Int, y: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getMinHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun getSeaLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun getKeepSpawnInMemory(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setKeepSpawnInMemory(keepLoaded: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isAutoSave(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAutoSave(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setDifficulty(difficulty: Difficulty) {
        TODO("Not yet implemented")
    }

    override fun getDifficulty(): Difficulty {
        TODO("Not yet implemented")
    }

    override fun getWorldFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getWorldType(): WorldType? {
        TODO("Not yet implemented")
    }

    override fun canGenerateStructures(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isHardcore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setHardcore(hardcore: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAnimalSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerAnimalSpawns(ticksPerAnimalSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerMonsterSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerMonsterSpawns(ticksPerMonsterSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerWaterSpawns(ticksPerWaterSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterAmbientSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerWaterAmbientSpawns(ticksPerAmbientSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAmbientSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerAmbientSpawns(ticksPerAmbientSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getMonsterSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setMonsterSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setAnimalSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getWaterAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setWaterAnimalSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getWaterAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setWaterAmbientSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setAmbientSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getGameRules(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getGameRuleValue(rule: String?): String? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getGameRuleValue(rule: GameRule<T>): T? {
        TODO("Not yet implemented")
    }

    override fun setGameRuleValue(rule: String, value: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isGameRule(rule: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getGameRuleDefault(rule: GameRule<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> setGameRule(rule: GameRule<T>, newValue: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorldBorder(): WorldBorder {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(particle: Particle, location: Location, count: Int) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(particle: Particle, x: Double, y: Double, z: Double, count: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(particle: Particle, location: Location, count: Int, data: T?) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(particle: Particle, x: Double, y: Double, z: Double, count: Int, data: T?) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        receivers: MutableList<Player>?,
        source: Player?,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?,
        force: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?,
        force: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?,
        force: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun locateNearestStructure(
        origin: Location,
        structureType: StructureType,
        radius: Int,
        findUnexplored: Boolean
    ): Location? {
        TODO("Not yet implemented")
    }

    override fun locateNearestBiome(origin: Location, biome: Biome, radius: Int): Location? {
        TODO("Not yet implemented")
    }

    override fun locateNearestBiome(origin: Location, biome: Biome, radius: Int, step: Int): Location? {
        TODO("Not yet implemented")
    }

    override fun isUltrawarm(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isNatural(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCoordinateScale(): Double {
        TODO("Not yet implemented")
    }

    override fun hasSkylight(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasBedrockCeiling(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPiglinSafe(): Boolean {
        TODO("Not yet implemented")
    }

    override fun doesBedWork(): Boolean {
        TODO("Not yet implemented")
    }

    override fun doesRespawnAnchorWork(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasRaids(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isFixedTime(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getInfiniburn(): MutableCollection<Material> {
        TODO("Not yet implemented")
    }

    override fun getViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun setViewDistance(viewDistance: Int) {
        TODO("Not yet implemented")
    }

    override fun getNoTickViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun setNoTickViewDistance(viewDistance: Int) {
        TODO("Not yet implemented")
    }

    override fun spigot(): World.Spigot {
        TODO("Not yet implemented")
    }

    override fun locateNearestRaid(location: Location, radius: Int): Raid? {
        TODO("Not yet implemented")
    }

    override fun getRaids(): MutableList<Raid> {
        TODO("Not yet implemented")
    }

    override fun getEnderDragonBattle(): DragonBattle? {
        TODO("Not yet implemented")
    }
}