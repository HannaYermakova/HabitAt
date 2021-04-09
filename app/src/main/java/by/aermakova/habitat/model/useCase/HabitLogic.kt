package by.aermakova.habitat.model.useCase

import android.content.Context
import by.aermakova.habitat.R
import by.aermakova.habitat.model.model.HabitModel
import by.aermakova.habitat.model.utilenums.Weekday
import java.util.*

class HabitLogic(private val habit: HabitModel?, private val context: Context?) : ObjectLogic() {
    val progressText: String
        get() = "$currentTimeProgress%"

    private val currentTimeProgress: Double
        get() {
            val daysHaveToBeMarked = countDaysHaveToBeMarked()
            val selectedDays = habit?.markedDays
            return (if (daysHaveToBeMarked > 0) selectedDays!! * 100.0 / daysHaveToBeMarked else 0) as Double
        }

    /**
     * Monday accepted as the first day of the week.
     *
     * @return the first day of the habit as index in weekDays array in [HabitModel]
     */
    private val startDayOfTheWeek: Int
        get() {
            val calendar = Calendar.getInstance()
            calendar.time = Date(habit!!.startTime)
            val index = calendar[Calendar.DAY_OF_WEEK]
            return if (index == 1) 6 else index - 2
        }

    private fun countDaysHaveToBeMarked(): Int {
        var daysHaveToBeMarked = 0
        val startDayOfTheWeek = startDayOfTheWeek
        val pattern = habit?.weekDays
        var i = 0
        var j = startDayOfTheWeek
        while (i < habit!!.day) {
            if (pattern!![j]) ++daysHaveToBeMarked
            if (j == pattern.size - 1) {
                j = -1
            }
            i++
            j++
        }
        return daysHaveToBeMarked
    }

    val allDaysText: String?
        get() {
            val resources = context!!.resources
            val nominative = resources.getString(R.string.nominative_days)
            val genitive = resources.getString(R.string.genitive_days)
            val accusative = resources.getString(R.string.accusative_days)
            return getStringCount(habit!!.day, nominative, genitive, accusative)
        }

    val weekDaysText: String
        get() {
            val stringBuilder = StringBuilder()
            val weekDaysList: Array<Weekday> = Weekday.week
            var week = 0
            val weekDays = habit!!.weekDays
            for (i in 0..6) {
                if (weekDays[i]) {
                    week++
                    val day = context?.getString(weekDaysList[i].dayTitleId)
                    stringBuilder.append(day).append(", ")
                }
            }
            return if (stringBuilder.isNotEmpty()) {
                if (week == 7) {
                    return "Каждый день"
                }
                stringBuilder.deleteCharAt(stringBuilder.length - 2)
                stringBuilder.toString().trim { it <= ' ' }
            } else "Нет выбранных дней недели"
        }
}