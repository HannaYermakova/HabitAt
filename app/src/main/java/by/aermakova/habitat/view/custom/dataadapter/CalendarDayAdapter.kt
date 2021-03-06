package by.aermakova.habitat.view.custom.dataadapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.useCase.CalendarDay
import by.aermakova.habitat.model.utilenums.WeekDay
import by.aermakova.habitat.view.custom.CalendarDayView

class CalendarDayAdapter : RecyclerView.Adapter<CalendarDayAdapter.CalendarDayHolder>() {
    private val calendarDays: Array<CalendarDay> = generateWeek()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDayHolder {
        Log.d("A_CalendarDayAdapter","${calendarDays[0].weekDay.day}")
        return CalendarDayHolder(CalendarDayView(parent.context))
    }

    override fun getItemCount(): Int {
        return calendarDays.size
    }

    override fun onBindViewHolder(holder: CalendarDayHolder, position: Int) {
        Log.d("A_CalendarDayAdapter","${calendarDays[0].weekDay.day}")

        val view = holder.view
        view.setCalendarDay(calendarDays[position])
    }

    class CalendarDayHolder(val view: CalendarDayView) : RecyclerView.ViewHolder(view)

    private fun generateWeek(): Array<CalendarDay> {
        val weekDays: Array<WeekDay> = WeekDay.week
        val calendarDays: Array<CalendarDay> =
         Array(7) { i ->
            CalendarDay(weekDays[i], hasHabits = true, isToday = true, dayNumber = 15)
        }
        Log.d("A_CalendarDayAdapter","${calendarDays.size}")
        return calendarDays
}
}
