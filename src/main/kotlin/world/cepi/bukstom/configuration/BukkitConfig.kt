package world.cepi.bukstom.configuration

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.nio.file.Path
import kotlin.io.path.Path

@Serializable
data class BukkitConfig(
    val settings: Settings = Settings(),
    @SerialName("spawn-limits")
    val spawnLimits: SpawnLimits = SpawnLimits(),
    @SerialName("chunk-gc")
    val chunkGc: ChunkGC = ChunkGC(),
    @SerialName("ticks-per")
    val ticksPer: TicksPer = TicksPer(),
    @Serializable(PathSerializer::class)
    val aliases: Path = Path("commands.yml")
) {
    @Serializable
    data class Settings(
        @SerialName("allow-end")
        val allowEnd: Boolean = true,
        @SerialName("warn-on-overload")
        val warnOnOverload: Boolean = true,
        @SerialName("permissions-file")
        @Serializable(PathSerializer::class)
        val permissionsFile: Path = Path("permissions.yml"),
        @SerialName("update-folder")
        @Serializable(PathSerializer::class)
        val updateFolder: Path = Path("update"),
        @SerialName("plugin-profiling")
        val pluginProfiling: Boolean = false,
        @SerialName("connection-throttle")
        val connectionThrottle: Long = 4000L,
        @SerialName("query-plugins")
        val queryPlugins: Boolean = true,
        @SerialName("deprecated-verbose")
        val deprecatedVerbose: BooleanWithDefault = BooleanWithDefault.Default,
        @SerialName("shutdown-message")
        val shutdownMessage: String = "Server closed"
    ) {
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(BooleanWithDefault.BooleanWithDefaultSerializer::class)
        sealed class BooleanWithDefault {
            @Serializer(BooleanWithDefault::class)
            object BooleanWithDefaultSerializer {
                override fun deserialize(decoder: Decoder): BooleanWithDefault = when (decoder.decodeString()) {
                    "true" -> True
                    "false" -> False
                    "default" -> Default
                    else -> throw SerializationException("Illegal argument")
                }


                override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BooleanWithDefault", PrimitiveKind.STRING)

                override fun serialize(encoder: Encoder, value: BooleanWithDefault) {
                    encoder.encodeString(when (value) {
                        True -> "true"
                        Default -> "default"
                        False -> "false"
                    })
                }
            }
            @Serializable(BooleanWithDefaultSerializer::class)
            object True : BooleanWithDefault()
            @Serializable(BooleanWithDefaultSerializer::class)
            object False : BooleanWithDefault()
            @Serializable(BooleanWithDefaultSerializer::class)
            object Default : BooleanWithDefault()
        }
    }
    @Serializable
    data class SpawnLimits(
        val monsters: Int = 70,
        val animals: Int = 10,
        @SerialName("water-animals")
        val waterAnimals: Int = 15,
        @SerialName("water-ambient")
        val waterAmbient: Int = 20,
        val ambient: Int = 15
    )
    @Serializable
    data class ChunkGC(
        @SerialName("period-in-ticks")
        val periodInTicks: Int = 600
    )
    @Serializable
    data class TicksPer(
        @SerialName("animal-spawns")
        val animalSpawns: Int = 400,
        @SerialName("monster-spawns")
        val monsterSpawns: Int = 1,
        @SerialName("water-spawns")
        val waterSpawns: Int = 1,
        @SerialName("water-ambient-spawns")
        val waterAmbientSpawns: Int = 1,
        @SerialName("ambient-spawns")
        val ambientSpawns: Int = 1,
        val autosave: Int = 6000
    )
}