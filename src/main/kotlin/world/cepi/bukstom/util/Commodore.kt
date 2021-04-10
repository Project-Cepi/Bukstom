import com.google.common.io.ByteStreams
import joptsimple.OptionParser
import joptsimple.OptionSpec
import java.io.File
import java.io.FileOutputStream
import java.util.Arrays
import java.util.Enumeration
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
import org.bukkit.Material
import org.bukkit.plugin.AuthorNagException
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type



object Commodore {
    private val EVIL: Set<String> = setOf(
            "org/bukkit/World (III)I getBlockTypeIdAt",
            "org/bukkit/World (Lorg/bukkit/Location;)I getBlockTypeIdAt",
            "org/bukkit/block/Block ()I getTypeId",
            "org/bukkit/block/Block (I)Z setTypeId",
            "org/bukkit/block/Block (IZ)Z setTypeId",
            "org/bukkit/block/Block (IBZ)Z setTypeIdAndData",
            "org/bukkit/block/Block (B)V setData",
            "org/bukkit/block/Block (BZ)V setData",
            "org/bukkit/inventory/ItemStack ()I getTypeId",
            "org/bukkit/inventory/ItemStack (I)V setTypeId"
        )
    fun convert(b: ByteArray?, modern: Boolean): ByteArray {
        val cr = ClassReader(b)
        val cw = ClassWriter(cr, 0)
        cr.accept(object : ClassVisitor(Opcodes.ASM8, cw) {
            override fun visitMethod(
                access: Int,
                name: String?,
                desc: String?,
                signature: String?,
                exceptions: Array<String?>?
            ): MethodVisitor {
                return object : MethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions)) {
                    override fun visitFieldInsn(opcode: Int, owner: String, name: String, desc: String?) {
                        var name = name
                        if (owner == "org/bukkit/block/Biome") {
                            when (name) {
                                "NETHER" -> {
                                    super.visitFieldInsn(opcode, owner, "NETHER_WASTES", desc)
                                    return
                                }
                            }
                        }
                        if (owner == "org/bukkit/entity/EntityType") {
                            when (name) {
                                "PIG_ZOMBIE" -> {
                                    super.visitFieldInsn(opcode, owner, "ZOMBIFIED_PIGLIN", desc)
                                    return
                                }
                            }
                        }
                        if (modern) {
                            if (owner == "org/bukkit/Material") {
                                when (name) {
                                    "CACTUS_GREEN" -> name = "GREEN_DYE"
                                    "DANDELION_YELLOW" -> name = "YELLOW_DYE"
                                    "ROSE_RED" -> name = "RED_DYE"
                                    "SIGN" -> name = "OAK_SIGN"
                                    "WALL_SIGN" -> name = "OAK_WALL_SIGN"
                                    "ZOMBIE_PIGMAN_SPAWN_EGG" -> name = "ZOMBIFIED_PIGLIN_SPAWN_EGG"
                                }
                            }
                            super.visitFieldInsn(opcode, owner, name, desc)
                            return
                        }
                        if (owner == "org/bukkit/Material") {
                            try {
                                Material.valueOf("LEGACY_$name")
                            } catch (ex: IllegalArgumentException) {
                                throw AuthorNagException("No legacy enum constant for $name. Did you forget to define a modern (1.13+) api-version in your plugin.yml?")
                            }
                            super.visitFieldInsn(opcode, owner, "LEGACY_$name", desc)
                            return
                        }
                        if (owner == "org/bukkit/Art") {
                            when (name) {
                                "BURNINGSKULL" -> {
                                    super.visitFieldInsn(opcode, owner, "BURNING_SKULL", desc)
                                    return
                                }
                                "DONKEYKONG" -> {
                                    super.visitFieldInsn(opcode, owner, "DONKEY_KONG", desc)
                                    return
                                }
                            }
                        }
                        if (owner == "org/bukkit/DyeColor") {
                            when (name) {
                                "SILVER" -> {
                                    super.visitFieldInsn(opcode, owner, "LIGHT_GRAY", desc)
                                    return
                                }
                            }
                        }
                        if (owner == "org/bukkit/Particle") {
                            when (name) {
                                "BLOCK_CRACK", "BLOCK_DUST", "FALLING_DUST" -> {
                                    super.visitFieldInsn(opcode, owner, "LEGACY_$name", desc)
                                    return
                                }
                            }
                        }
                        super.visitFieldInsn(opcode, owner, name, desc)
                    }

                    override fun visitMethodInsn(opcode: Int, owner: String, name: String, desc: String, itf: Boolean) {
                        // SPIGOT-4496
                        if (owner == "org/bukkit/map/MapView" && name == "getId" && desc == "()S") {
                            // Should be same size on stack so just call other method
                            super.visitMethodInsn(opcode, owner, name, "()I", itf)
                            return
                        }
                        // SPIGOT-4608
                        if ((owner == "org/bukkit/Bukkit" || owner == "org/bukkit/Server") && name == "getMap" && desc == "(S)Lorg/bukkit/map/MapView;") {
                            // Should be same size on stack so just call other method
                            super.visitMethodInsn(opcode, owner, name, "(I)Lorg/bukkit/map/MapView;", itf)
                            return
                        }
                        if (modern) {
                            if (owner == "org/bukkit/Material") {
                                when (name) {
                                    "values" -> {
                                        super.visitMethodInsn(
                                            opcode, "org/bukkit/craftbukkit/util/CraftLegacy",
                                            "modern_$name", desc, itf
                                        )
                                        return
                                    }
                                    "ordinal" -> {
                                        super.visitMethodInsn(
                                            Opcodes.INVOKESTATIC, "org/bukkit/craftbukkit/util/CraftLegacy",
                                            "modern_$name", "(Lorg/bukkit/Material;)I", false
                                        )
                                        return
                                    }
                                }
                            }
                            super.visitMethodInsn(opcode, owner, name, desc, itf)
                            return
                        }
                        if (owner == "org/bukkit/ChunkSnapshot" && name == "getBlockData" && desc == "(III)I") {
                            super.visitMethodInsn(opcode, owner, "getData", desc, itf)
                            return
                        }
                        val retType: Type = Type.getReturnType(desc)
                        if (EVIL.contains("$owner $desc $name")
                            || owner.startsWith("org/bukkit/block/") && "$desc $name" == "()I getTypeId"
                            || owner.startsWith("org/bukkit/block/") && "$desc $name" == "(I)Z setTypeId"
                            || owner.startsWith("org/bukkit/block/") && "$desc $name" == "()Lorg/bukkit/Material; getType"
                        ) {
                            val args: Array<Type> = Type.getArgumentTypes(desc)
                            val newArgs: Array<Type?> = arrayOfNulls(args.size + 1)
                            newArgs[0] = Type.getObjectType(owner)
                            System.arraycopy(args, 0, newArgs, 1, args.size)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "org/bukkit/craftbukkit/legacy/CraftEvil",
                                name,
                                Type.getMethodDescriptor(retType, *newArgs),
                                false
                            )
                            return
                        }
                        if (owner == "org/bukkit/DyeColor") {
                            if (name == "valueOf" && desc == "(Ljava/lang/String;)Lorg/bukkit/DyeColor;") {
                                super.visitMethodInsn(opcode, owner, "legacyValueOf", desc, itf)
                                return
                            }
                        }
                        if (owner == "org/bukkit/Material") {
                            if (name == "getMaterial" && desc == "(I)Lorg/bukkit/Material;") {
                                super.visitMethodInsn(
                                    opcode,
                                    "org/bukkit/craftbukkit/legacy/CraftEvil",
                                    name,
                                    desc,
                                    itf
                                )
                                return
                            }
                            when (name) {
                                "values", "valueOf", "getMaterial", "matchMaterial" -> {
                                    super.visitMethodInsn(
                                        opcode,
                                        "org/bukkit/craftbukkit/legacy/CraftLegacy",
                                        name,
                                        desc,
                                        itf
                                    )
                                    return
                                }
                                "ordinal" -> {
                                    super.visitMethodInsn(
                                        Opcodes.INVOKESTATIC,
                                        "org/bukkit/craftbukkit/legacy/CraftLegacy",
                                        "ordinal",
                                        "(Lorg/bukkit/Material;)I",
                                        false
                                    )
                                    return
                                }
                                "name", "toString" -> {
                                    super.visitMethodInsn(
                                        Opcodes.INVOKESTATIC,
                                        "org/bukkit/craftbukkit/legacy/CraftLegacy",
                                        name,
                                        "(Lorg/bukkit/Material;)Ljava/lang/String;",
                                        false
                                    )
                                    return
                                }
                            }
                        }
                        if (retType.sort == Type.OBJECT && retType.internalName
                                .equals("org/bukkit/Material") && owner.startsWith("org/bukkit")
                        ) {
                            super.visitMethodInsn(opcode, owner, name, desc, itf)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "org/bukkit/craftbukkit/legacy/CraftLegacy",
                                "toLegacy",
                                "(Lorg/bukkit/Material;)Lorg/bukkit/Material;",
                                false
                            )
                            return
                        }
                        super.visitMethodInsn(opcode, owner, name, desc, itf)
                    }
                }
            }
        }, 0)
        return cw.toByteArray()
    }
}