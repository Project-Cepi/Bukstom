package world.cepi.bukstom.util

import net.minestom.server.utils.Position
import org.bukkit.Location

fun Location.toPosition(): Position =
    Position(this.x, this.y, this.z, this.yaw, this.pitch)