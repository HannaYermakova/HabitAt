package by.aermakova.habitat.model.useCase

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import by.aermakova.habitat.model.db.entity.Habit
import java.util.*

class AlarmManagerLogic(private val context: Context, habit: Habit?) {
    private val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private fun setAllHabitAlarms(habit: Habit?) {
        if (habit!!.isNotificationEnable) {
            val table = habit.weekDays
            for (i in table!!.indices) {
                if (table[i]) {
                    val weekDay: Int = if (i == 6) 1 else i + 2
                    val intent = Intent(context, AlarmReceiver::class.java)
                    intent.putExtra(HABIT_TITLE_TAG, habit.title)
                    val alarmIntent = PendingIntent.getBroadcast(context, (habit.startTime + i).toInt(), intent, 0)
                    setAlarm(habit.hours, habit.minute, weekDay, alarmIntent)
                }
            }
        }
    }

    private fun setAlarm(hour: Int, minute: Int, weekDay: Int, alarmIntent: PendingIntent) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = hour
        calendar[Calendar.MINUTE] = minute
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.DAY_OF_WEEK] = weekDay
        println(calendar.time)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY * 7, alarmIntent)
    }

    companion object {
        private const val HABIT_TITLE_TAG = "habit_title"
    }

    init {
        setAllHabitAlarms(habit)
    }
}