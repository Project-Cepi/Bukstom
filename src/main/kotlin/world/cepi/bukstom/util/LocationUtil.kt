package world.cepi.bukstom.util

import net.minestom.server.entity.Entity
import net.minestom.server.utils.Position
import net.minestom.server.utils.Vector
import org.bukkit.Location
import world.cepi.bukstom.MinestomWorld

fun Location.toPosition(): Position =
    Position(this.x, this.y, this.z, this.yaw, this.pitch)

fun Entity.teleport(location: Location) {
    this.setInstance(
        (location.world as? MinestomWorld)?.instance ?: return,
        location.toPosition()
    )
}

fun Position.toLocation(world: MinestomWorld): Location =
    Location(world, this.x, this.y, this.z, this.yaw, this.pitch)

fun org.bukkit.util.Vector.toMinestomVector(): Vector =
    Vector(this.x, this.y, this.z)