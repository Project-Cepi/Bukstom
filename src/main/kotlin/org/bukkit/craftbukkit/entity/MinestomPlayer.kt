package org.bukkit.craftbukkit.entity

import net.minestom.server.chat.ColoredText
import net.minestom.server.resourcepack.ResourcePack
import org.bukkit.*
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.PistonMoveReaction
import org.bukkit.block.data.BlockData
import org.bukkit.conversations.Conversation
import org.bukkit.conversations.ConversationAbandonedEvent
import org.bukkit.entity.*
import org.bukkit.entity.memory.MemoryKey
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.*
import org.bukkit.map.MapView
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.util.BoundingBox
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import java.net.InetSocketAddress
import java.util.*

class MinestomPlayer(val minestomPlayer: net.minestom.server.entity.Player): Player {

    override fun getAttribute(attribute: Attribute): AttributeInstance? {
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

    override fun isOp(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setOp(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isPermissionSet(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPermissionSet(perm: Permission): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPermission(name: String): Boolean {
        return minestomPlayer.hasPermission(name)
    }

    override fun hasPermission(perm: Permission): Boolean {
        return minestomPlayer.hasPermission(perm.name)
    }

    override fun addAttachment(plugin: Plugin, name: String, value: Boolean): PermissionAttachment {
        TODO("Not yet implemented")
    }

    override fun addAttachment(plugin: Plugin): PermissionAttachment {
        TODO("Not yet implemented")
    }

    override fun addAttachment(plugin: Plugin, name: String, value: Boolean, ticks: Int): PermissionAttachment? {
        TODO("Not yet implemented")
    }

    override fun addAttachment(plugin: Plugin, ticks: Int): PermissionAttachment? {
        TODO("Not yet implemented")
    }

    override fun removeAttachment(attachment: PermissionAttachment) {
        TODO("Not yet implemented")
    }

    override fun recalculatePermissions() {
        return // unnecessary
    }

    override fun getEffectivePermissions(): MutableSet<PermissionAttachmentInfo> {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: String) {
        minestomPlayer.sendMessage(message)
    }

    override fun sendMessage(messages: Array<out String>) {
        messages.forEach { minestomPlayer.sendMessage(it) }
    }

    override fun sendMessage(sender: UUID?, message: String) {
        minestomPlayer.sendMessage(message)
    }

    override fun sendMessage(sender: UUID?, messages: Array<out String>) {
        messages.forEach { minestomPlayer.sendMessage(it) }
    }

    override fun getServer(): Server {
        return Bukkit.getServer()
    }

    override fun getName(): String {
        return minestomPlayer.username
    }

    override fun spigot(): Player.Spigot {
        TODO("Not yet implemented")
    }

    override fun getCustomName(): String? {
        TODO("Not yet implemented")
    }

    override fun setCustomName(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getPersistentDataContainer(): PersistentDataContainer {
        TODO("Not yet implemented")
    }

    override fun getLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getLocation(loc: Location?): Location? {
        TODO("Not yet implemented")
    }

    override fun setVelocity(velocity: Vector) {
        TODO("Not yet implemented")
    }

    override fun getVelocity(): Vector {
        TODO("Not yet implemented")
    }

    override fun getHeight(): Double {
        TODO("Not yet implemented")
    }

    override fun getWidth(): Double {
        TODO("Not yet implemented")
    }

    override fun getBoundingBox(): BoundingBox {
        TODO("Not yet implemented")
    }

    override fun isOnGround(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorld(): World {
        TODO("Not yet implemented")
    }

    override fun setRotation(yaw: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun teleport(location: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(location: Location, cause: PlayerTeleportEvent.TeleportCause): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(destination: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(destination: Entity, cause: PlayerTeleportEvent.TeleportCause): Boolean {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(x: Double, y: Double, z: Double): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun getEntityId(): Int {
        TODO("Not yet implemented")
    }

    override fun getFireTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxFireTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setFireTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }

    override fun isDead(): Boolean {
        return minestomPlayer.isDead
    }

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPersistent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setPersistent(persistent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPassenger(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setPassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPassengers(): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun addPassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun eject(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getFallDistance(): Float {
        TODO("Not yet implemented")
    }

    override fun setFallDistance(distance: Float) {
        TODO("Not yet implemented")
    }

    override fun setLastDamageCause(event: EntityDamageEvent?) {
        TODO("Not yet implemented")
    }

    override fun getLastDamageCause(): EntityDamageEvent? {
        TODO("Not yet implemented")
    }

    override fun getUniqueId(): UUID {
        return minestomPlayer.uuid
    }

    override fun getTicksLived(): Int {
        TODO("Not yet implemented")
    }

    override fun setTicksLived(value: Int) {
        TODO("Not yet implemented")
    }

    override fun playEffect(loc: Location, effect: Effect, data: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(loc: Location, effect: Effect, data: T?) {
        TODO("Not yet implemented")
    }

    override fun playEffect(type: EntityEffect) {
        TODO("Not yet implemented")
    }

    override fun getType(): EntityType {
        return EntityType.PLAYER
    }

    override fun isInsideVehicle(): Boolean {
        TODO("Not yet implemented")
    }

    override fun leaveVehicle(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getVehicle(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setCustomNameVisible(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isCustomNameVisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGlowing(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isGlowing(): Boolean {
        return minestomPlayer.isGlowing
    }

    override fun setInvulnerable(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isInvulnerable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSilent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSilent(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasGravity(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGravity(gravity: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPortalCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setPortalCooldown(cooldown: Int) {
        TODO("Not yet implemented")
    }

    override fun getScoreboardTags(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun addScoreboardTag(tag: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeScoreboardTag(tag: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPistonMoveReaction(): PistonMoveReaction {
        TODO("Not yet implemented")
    }

    override fun getFacing(): BlockFace {
        TODO("Not yet implemented")
    }

    override fun getPose(): Pose {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double, source: Entity?) {
        TODO("Not yet implemented")
    }

    override fun getHealth(): Double {
        TODO("Not yet implemented")
    }

    override fun setHealth(health: Double) {
        TODO("Not yet implemented")
    }

    override fun getAbsorptionAmount(): Double {
        TODO("Not yet implemented")
    }

    override fun setAbsorptionAmount(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun getMaxHealth(): Double {
        TODO("Not yet implemented")
    }

    override fun setMaxHealth(health: Double) {
        TODO("Not yet implemented")
    }

    override fun resetMaxHealth() {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile?> launchProjectile(projectile: Class<out T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile?> launchProjectile(projectile: Class<out T>, velocity: Vector?): T {
        TODO("Not yet implemented")
    }

    override fun getEyeHeight(): Double {
        TODO("Not yet implemented")
    }

    override fun getEyeHeight(ignorePose: Boolean): Double {
        TODO("Not yet implemented")
    }

    override fun getEyeLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getLineOfSight(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
        TODO("Not yet implemented")
    }

    override fun getTargetBlock(transparent: MutableSet<Material>?, maxDistance: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getLastTwoTargetBlocks(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockExact(maxDistance: Int): Block? {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockExact(maxDistance: Int, fluidCollisionMode: FluidCollisionMode): Block? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(maxDistance: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(maxDistance: Double, fluidCollisionMode: FluidCollisionMode): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getRemainingAir(): Int {
        TODO("Not yet implemented")
    }

    override fun setRemainingAir(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getMaximumAir(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaximumAir(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getArrowCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setArrowCooldown(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getArrowsInBody(): Int {
        TODO("Not yet implemented")
    }

    override fun setArrowsInBody(count: Int) {
        TODO("Not yet implemented")
    }

    override fun getMaximumNoDamageTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaximumNoDamageTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getLastDamage(): Double {
        TODO("Not yet implemented")
    }

    override fun setLastDamage(damage: Double) {
        TODO("Not yet implemented")
    }

    override fun getNoDamageTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setNoDamageTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getKiller(): Player? {
        TODO("Not yet implemented")
    }

    override fun addPotionEffect(effect: PotionEffect): Boolean {
        TODO("Not yet implemented")
    }

    override fun addPotionEffect(effect: PotionEffect, force: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun addPotionEffects(effects: MutableCollection<PotionEffect>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPotionEffect(type: PotionEffectType): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPotionEffect(type: PotionEffectType): PotionEffect? {
        TODO("Not yet implemented")
    }

    override fun removePotionEffect(type: PotionEffectType) {
        TODO("Not yet implemented")
    }

    override fun getActivePotionEffects(): MutableCollection<PotionEffect> {
        TODO("Not yet implemented")
    }

    override fun hasLineOfSight(other: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRemoveWhenFarAway(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setRemoveWhenFarAway(remove: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getEquipment(): EntityEquipment? {
        TODO("Not yet implemented")
    }

    override fun setCanPickupItems(pickup: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getCanPickupItems(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLeashed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLeashHolder(): Entity {
        TODO("Not yet implemented")
    }

    override fun setLeashHolder(holder: Entity?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isGliding(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGliding(gliding: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSwimming(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSwimming(swimming: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isRiptiding(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSleeping(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAI(ai: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasAI(): Boolean {
        TODO("Not yet implemented")
    }

    override fun attack(target: Entity) {
        TODO("Not yet implemented")
    }

    override fun swingMainHand() {
        TODO("Not yet implemented")
    }

    override fun swingOffHand() {
        TODO("Not yet implemented")
    }

    override fun setCollidable(collidable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isCollidable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCollidableExemptions(): MutableSet<UUID> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getMemory(memoryKey: MemoryKey<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> setMemory(memoryKey: MemoryKey<T>, memoryValue: T?) {
        TODO("Not yet implemented")
    }

    override fun getCategory(): EntityCategory {
        TODO("Not yet implemented")
    }

    override fun setInvisible(invisible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isInvisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getInventory(): PlayerInventory {
        TODO("Not yet implemented")
    }

    override fun getEnderChest(): Inventory {
        TODO("Not yet implemented")
    }

    override fun getMainHand(): MainHand {
        TODO("Not yet implemented")
    }

    override fun setWindowProperty(prop: InventoryView.Property, value: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getOpenInventory(): InventoryView {
        TODO("Not yet implemented")
    }

    override fun openInventory(inventory: Inventory): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openInventory(inventory: InventoryView) {
        TODO("Not yet implemented")
    }

    override fun openWorkbench(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openEnchanting(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openMerchant(trader: Villager, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openMerchant(merchant: Merchant, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun closeInventory() {
        TODO("Not yet implemented")
    }

    override fun getItemInHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInHand(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun getItemOnCursor(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemOnCursor(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun hasCooldown(material: Material): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCooldown(material: Material): Int {
        TODO("Not yet implemented")
    }

    override fun setCooldown(material: Material, ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getSleepTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun sleep(location: Location, force: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun wakeup(setSpawnLocation: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getBedLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getGameMode(): GameMode {
        TODO("Not yet implemented")
    }

    override fun setGameMode(mode: GameMode) {
        TODO("Not yet implemented")
    }

    override fun isBlocking(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isHandRaised(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getExpToLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun getAttackCooldown(): Float {
        TODO("Not yet implemented")
    }

    override fun discoverRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun discoverRecipes(recipes: MutableCollection<NamespacedKey>): Int {
        TODO("Not yet implemented")
    }

    override fun undiscoverRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun undiscoverRecipes(recipes: MutableCollection<NamespacedKey>): Int {
        TODO("Not yet implemented")
    }

    override fun hasDiscoveredRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDiscoveredRecipes(): MutableSet<NamespacedKey> {
        TODO("Not yet implemented")
    }

    override fun getShoulderEntityLeft(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setShoulderEntityLeft(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun getShoulderEntityRight(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setShoulderEntityRight(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun dropItem(dropAll: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun isConversing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun acceptConversationInput(input: String) {
        TODO("Not yet implemented")
    }

    override fun beginConversation(conversation: Conversation): Boolean {
        TODO("Not yet implemented")
    }

    override fun abandonConversation(conversation: Conversation) {
        TODO("Not yet implemented")
    }

    override fun abandonConversation(conversation: Conversation, details: ConversationAbandonedEvent) {
        TODO("Not yet implemented")
    }

    override fun sendRawMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun sendRawMessage(sender: UUID?, message: String) {
        TODO("Not yet implemented")
    }

    override fun serialize(): MutableMap<String, Any> {
        TODO("Not yet implemented")
    }

    override fun isOnline(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBanned(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isWhitelisted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setWhitelisted(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPlayer(): Player? {
        TODO("Not yet implemented")
    }

    override fun getFirstPlayed(): Long {
        TODO("Not yet implemented")
    }

    override fun getLastPlayed(): Long {
        TODO("Not yet implemented")
    }

    override fun hasPlayedBefore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBedSpawnLocation(): Location? {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, material: Material) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, material: Material, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, entityType: EntityType) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, entityType: EntityType, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, material: Material) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, material: Material, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, entityType: EntityType) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, entityType: EntityType, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, material: Material, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, entityType: EntityType, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic): Int {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic, material: Material): Int {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic, entityType: EntityType): Int {
        TODO("Not yet implemented")
    }

    override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getDisplayName(): String {
        return minestomPlayer.displayName!!.rawMessage
    }

    override fun setDisplayName(name: String?) {
        minestomPlayer.displayName = ColoredText.of(name!!)
    }

    override fun getPlayerListName(): String {
        TODO("Not yet implemented")
    }

    override fun setPlayerListName(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getPlayerListHeader(): String? {
        TODO("Not yet implemented")
    }

    override fun getPlayerListFooter(): String? {
        TODO("Not yet implemented")
    }

    override fun setPlayerListHeader(header: String?) {
        TODO("Not yet implemented")
    }

    override fun setPlayerListFooter(footer: String?) {
        TODO("Not yet implemented")
    }

    override fun setPlayerListHeaderFooter(header: String?, footer: String?) {
        TODO("Not yet implemented")
    }

    override fun setCompassTarget(loc: Location) {
        TODO("Not yet implemented")
    }

    override fun getCompassTarget(): Location {
        TODO("Not yet implemented")
    }

    override fun getAddress(): InetSocketAddress? {
        TODO("Not yet implemented")
    }

    override fun kickPlayer(message: String?) {
        TODO("Not yet implemented")
    }

    override fun chat(msg: String) {
        TODO("Not yet implemented")
    }

    override fun performCommand(command: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSneaking(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSneaking(sneak: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSprinting(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSprinting(sprinting: Boolean) {
        TODO("Not yet implemented")
    }

    override fun saveData() {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }

    override fun setSleepingIgnored(isSleeping: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSleepingIgnored(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setBedSpawnLocation(location: Location?) {
        TODO("Not yet implemented")
    }

    override fun setBedSpawnLocation(location: Location?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun playNote(loc: Location, instrument: Byte, note: Byte) {
        TODO("Not yet implemented")
    }

    override fun playNote(loc: Location, instrument: Instrument, note: Note) {
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

    override fun stopSound(sound: Sound) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: String) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: Sound, category: SoundCategory?) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: String, category: SoundCategory?) {
        TODO("Not yet implemented")
    }

    override fun sendBlockChange(loc: Location, material: Material, data: Byte) {
        TODO("Not yet implemented")
    }

    override fun sendBlockChange(loc: Location, block: BlockData) {
        TODO("Not yet implemented")
    }

    override fun sendChunkChange(loc: Location, sx: Int, sy: Int, sz: Int, data: ByteArray): Boolean {
        TODO("Not yet implemented")
    }

    override fun sendSignChange(loc: Location, lines: Array<out String>?) {
        TODO("Not yet implemented")
    }

    override fun sendSignChange(loc: Location, lines: Array<out String>?, dyeColor: DyeColor) {
        TODO("Not yet implemented")
    }

    override fun sendMap(map: MapView) {
        TODO("Not yet implemented")
    }

    override fun updateInventory() {
        TODO("Not yet implemented")
    }

    override fun setPlayerTime(time: Long, relative: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPlayerTime(): Long {
        TODO("Not yet implemented")
    }

    override fun getPlayerTimeOffset(): Long {
        TODO("Not yet implemented")
    }

    override fun isPlayerTimeRelative(): Boolean {
        TODO("Not yet implemented")
    }

    override fun resetPlayerTime() {
        TODO("Not yet implemented")
    }

    override fun setPlayerWeather(type: WeatherType) {
        TODO("Not yet implemented")
    }

    override fun getPlayerWeather(): WeatherType? {
        TODO("Not yet implemented")
    }

    override fun resetPlayerWeather() {
        TODO("Not yet implemented")
    }

    override fun giveExp(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun giveExpLevels(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun getExp(): Float {
        TODO("Not yet implemented")
    }

    override fun setExp(exp: Float) {
        TODO("Not yet implemented")
    }

    override fun getLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun setLevel(level: Int) {
        TODO("Not yet implemented")
    }

    override fun getTotalExperience(): Int {
        TODO("Not yet implemented")
    }

    override fun setTotalExperience(exp: Int) {
        TODO("Not yet implemented")
    }

    override fun sendExperienceChange(progress: Float) {
        TODO("Not yet implemented")
    }

    override fun sendExperienceChange(progress: Float, level: Int) {
        TODO("Not yet implemented")
    }

    override fun getExhaustion(): Float {
        TODO("Not yet implemented")
    }

    override fun setExhaustion(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getSaturation(): Float {
        TODO("Not yet implemented")
    }

    override fun setSaturation(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getFoodLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun setFoodLevel(value: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllowFlight(): Boolean {
        return minestomPlayer.isAllowFlying
    }

    override fun setAllowFlight(flight: Boolean) {
        minestomPlayer.isAllowFlying = flight
    }

    override fun hidePlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun hidePlayer(plugin: Plugin, player: Player) {
        TODO("Not yet implemented")
    }

    override fun showPlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun showPlayer(plugin: Plugin, player: Player) {
        TODO("Not yet implemented")
    }

    override fun canSee(player: Player): Boolean {
        TODO("Not yet implemented")
    }

    override fun isFlying(): Boolean {
        return minestomPlayer.isFlying
    }

    override fun setFlying(value: Boolean) {
        minestomPlayer.isFlying = value
    }

    override fun setFlySpeed(value: Float) {
        TODO("Not yet implemented")
    }

    override fun setWalkSpeed(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getFlySpeed(): Float {
        TODO("Not yet implemented")
    }

    override fun getWalkSpeed(): Float {
        TODO("Not yet implemented")
    }

    override fun setTexturePack(url: String) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String) {
        minestomPlayer.setResourcePack(ResourcePack(url, null))
    }

    override fun setResourcePack(url: String, hash: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getScoreboard(): Scoreboard {
        TODO("Not yet implemented")
    }

    override fun setScoreboard(scoreboard: Scoreboard) {
        TODO("Not yet implemented")
    }

    override fun isHealthScaled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setHealthScaled(scale: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setHealthScale(scale: Double) {
        TODO("Not yet implemented")
    }

    override fun getHealthScale(): Double {
        TODO("Not yet implemented")
    }

    override fun getSpectatorTarget(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setSpectatorTarget(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun sendTitle(title: String?, subtitle: String?) {
        TODO("Not yet implemented")
    }

    override fun sendTitle(title: String?, subtitle: String?, fadeIn: Int, stay: Int, fadeOut: Int) {
        TODO("Not yet implemented")
    }

    override fun resetTitle() {
        minestomPlayer.resetTitle()
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
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun getAdvancementProgress(advancement: Advancement): AdvancementProgress {
        TODO("Not yet implemented")
    }

    override fun getClientViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun getLocale(): String {
        TODO("Not yet implemented")
    }

    override fun updateCommands() {
        TODO("Not yet implemented")
    }

    override fun openBook(book: ItemStack) {
        TODO("Not yet implemented")
    }
}