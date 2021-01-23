package org.bukkit.craftbukkit

import org.bukkit.command.Command
import org.bukkit.help.*


/**
 * Standard implementation of [HelpMap] for CraftBukkit servers.
 */
class MinestomHelpMap(server: MinestomServer) : HelpMap {

    private val defaultTopic: HelpTopic? = null
    private val helpTopics: MutableMap<String, HelpTopic> = mutableMapOf()
    private val topicFactoryMap: Map<Class<*>, HelpTopicFactory<Command>> = mapOf()


    override fun getHelpTopic(topicName: String): HelpTopic? {
        TODO("Not yet implemented")
    }

    override fun getHelpTopics(): MutableCollection<HelpTopic> {
        TODO("Not yet implemented")
    }

    override fun addTopic(topic: HelpTopic) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        helpTopics.clear()
    }

    override fun registerHelpTopicFactory(commandClass: Class<*>, factory: HelpTopicFactory<*>) {
        TODO("Not yet implemented")
    }

    override fun getIgnoredPlugins(): MutableList<String> {
        TODO("Not yet implemented")
    }

}
