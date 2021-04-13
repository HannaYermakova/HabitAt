package by.aermakova.habitat.model.useCase.calendar

import android.annotation.SuppressLint
import by.aermakova.habitat.model.utilenums.Weekday
import by.aermakova.habitat.util.Constants.WEEK_SIZE
import java.text.SimpleDateFormat
import java.util.*

object CalendarConverter {

    fun generateWeek(): Array<Day> {
        val todayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val today = if (todayOfWeek in 2..7) todayOfWeek - 2 else 6
        val weekDays = Weekday.week
        val daysNumber = getDaysNumbers()
        return Array(WEEK_SIZE) { i -> Day(weekDays[i], daysNumber[i], i == today) }
    }

     @SuppressLint("SimpleDateFormat")
     private fun getDaysNumbers() : Array<Int> {
        val array = Array(WEEK_SIZE){0}
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val dateFormat = SimpleDateFormat("dd")
        array[0] = dateFormat.format(calendar.time).toInt()
        for (i in 1..6) {
            calendar.add(Calendar.DATE, 1)
            array[i] = dateFormat.format(calendar.time).toInt()
        }
        return array
    }

    data class Day(
        val weekday: Weekday,
        val number: Int,
        val isToday: Boolean
    )
}