package world.cepi.bukstom.command

import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.plugin.Plugin
import java.util.*

class MinestomCommandSender(val commandSender: net.minestom.server.command.CommandSender): CommandSender {
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
        return commandSender.hasPermission(name)
    }

    override fun hasPermission(perm: Permission): Boolean {
        return commandSender.hasPermission(perm.name)
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
        TODO("Not yet implemented")
    }

    override fun getEffectivePermissions(): MutableSet<PermissionAttachmentInfo> {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: String) {
        commandSender.sendMessage(message)
    }

    override fun sendMessage(messages: Array<out String>) {
        messages.forEach { sendMessage(it) }
    }

    // TODO why do you need a sender UUID?
    override fun sendMessage(sender: UUID?, message: String) {
        commandSender.sendMessage(message)
    }

    // TODO why do you need a sender UUID?
    override fun sendMessage(sender: UUID?, messages: Array<out String>) {
        sendMessage(messages)
    }

    override fun getServer(): Server {
        return Bukkit.getServer()
    }

    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun spigot(): CommandSender.Spigot {
        TODO("Not yet implemented")
    }
}