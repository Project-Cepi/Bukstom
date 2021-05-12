package world.cepi.bukstom.scheduler

import net.minestom.server.timer.TaskStatus
import net.minestom.server.utils.time.TimeUnit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask
import org.bukkit.scheduler.BukkitWorker
import world.cepi.kstom.Manager
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Future
import java.util.function.Consumer

class MinestomScheduler : BukkitScheduler {
    override fun scheduleSyncDelayedTask(plugin: Plugin, task: Runnable, delay: Long): Int =
        Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule().id

    override fun scheduleSyncDelayedTask(plugin: Plugin, task: BukkitRunnable, delay: Long) =
        Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule().id

    override fun scheduleSyncDelayedTask(plugin: Plugin, task: Runnable): Int =
        Manager.scheduler.buildTask(task).schedule().id

    override fun scheduleSyncDelayedTask(plugin: Plugin, task: BukkitRunnable): Int =
        Manager.scheduler.buildTask(task).schedule().id

    override fun scheduleSyncRepeatingTask(plugin: Plugin, task: Runnable, delay: Long, period: Long): Int =
        Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK).schedule().id

    override fun scheduleSyncRepeatingTask(plugin: Plugin, task: BukkitRunnable, delay: Long, period: Long): Int =
        Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK).schedule().id

    override fun scheduleAsyncDelayedTask(plugin: Plugin, task: Runnable, delay: Long): Int =
        Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule().id

    override fun scheduleAsyncDelayedTask(plugin: Plugin, task: Runnable): Int =
        Manager.scheduler.buildTask(task).schedule().id

    override fun scheduleAsyncRepeatingTask(plugin: Plugin, task: Runnable, delay: Long, period: Long): Int =
        Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK).schedule().id

    override fun <T : Any?> callSyncMethod(plugin: Plugin, task: Callable<T>): Future<T> {
        TODO("Not yet implemented")
    }

    override fun cancelTask(taskId: Int) {
        Manager.scheduler.getTask(taskId)?.let { Manager.scheduler.removeTask(it) }
    }

    override fun cancelTasks(plugin: Plugin) {
        Manager.scheduler.tasks.forEach {
            Manager.scheduler.removeTask(it)
        }
    }

    override fun isCurrentlyRunning(taskId: Int) = when (Manager.scheduler.getTask(taskId).status) {
        TaskStatus.SCHEDULED -> false
        TaskStatus.FINISHED -> false
        TaskStatus.CANCELLED -> false
    }


    override fun isQueued(taskId: Int) = when (Manager.scheduler.getTask(taskId).status) {
        TaskStatus.SCHEDULED -> true
        TaskStatus.FINISHED -> false
        TaskStatus.CANCELLED -> false
    }

    override fun getActiveWorkers(): MutableList<BukkitWorker> {
        TODO("Not yet implemented")
    }

    override fun getPendingTasks(): MutableList<BukkitTask> {
        TODO("Not yet implemented")
    }

    override fun runTask(plugin: Plugin, task: Runnable): BukkitTask =
        MinestomTask(Manager.scheduler.buildTask(task).schedule(), plugin)

    override fun runTask(plugin: Plugin, task: Consumer<BukkitTask>) {
        TODO("Not yet implemented")
    }

    override fun runTask(plugin: Plugin, task: BukkitRunnable) =
        MinestomTask(Manager.scheduler.buildTask(task).schedule(), plugin)

    override fun runTaskAsynchronously(plugin: Plugin, task: Runnable): BukkitTask =
        MinestomTask(Manager.scheduler.buildTask(task).schedule(), plugin)

    override fun runTaskAsynchronously(plugin: Plugin, task: Consumer<BukkitTask>) {
        TODO("Not yet implemented")
    }

    override fun runTaskAsynchronously(plugin: Plugin, task: BukkitRunnable) =
        MinestomTask(Manager.scheduler.buildTask(task).schedule(), plugin)

    override fun runTaskLater(plugin: Plugin, task: Runnable, delay: Long): BukkitTask =
        MinestomTask(Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule(), plugin)

    override fun runTaskLater(plugin: Plugin, task: Consumer<BukkitTask>, delay: Long) {
        TODO("Not yet implemented")
    }

    override fun runTaskLater(plugin: Plugin, task: BukkitRunnable, delay: Long): BukkitTask =
        MinestomTask(Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule(), plugin)

    override fun runTaskLaterAsynchronously(plugin: Plugin, task: Runnable, delay: Long) =
        MinestomTask(Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule(), plugin)

    override fun runTaskLaterAsynchronously(plugin: Plugin, task: Consumer<BukkitTask>, delay: Long) {
        TODO("Not yet implemented")
    }

    override fun runTaskLaterAsynchronously(plugin: Plugin, task: BukkitRunnable, delay: Long) =
        MinestomTask(Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule(), plugin)

    override fun runTaskTimer(plugin: Plugin, task: Runnable, delay: Long, period: Long) =
        MinestomTask(Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK).schedule(), plugin)

    override fun runTaskTimer(plugin: Plugin, task: Consumer<BukkitTask>, delay: Long, period: Long) {
        TODO("Not yet implemented")
    }

    override fun runTaskTimer(plugin: Plugin, task: BukkitRunnable, delay: Long, period: Long) =
        MinestomTask(Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK).schedule(), plugin)

    override fun runTaskTimerAsynchronously(plugin: Plugin, task: Runnable, delay: Long, period: Long) =
        MinestomTask(Manager.scheduler.buildTask(task).delay(delay, TimeUnit.TICK).schedule(), plugin)

    override fun runTaskTimerAsynchronously(plugin: Plugin, task: Consumer<BukkitTask>, delay: Long, period: Long) {
        TODO("Not yet implemented")
    }

    override fun runTaskTimerAsynchronously(
        plugin: Plugin,
        task: BukkitRunnable,
        delay: Long,
        period: Long
    ): BukkitTask {
        TODO("Not yet implemented")
    }

    override fun getMainThreadExecutor(plugin: Plugin): Executor {
        TODO("Not yet implemented")
    }
}