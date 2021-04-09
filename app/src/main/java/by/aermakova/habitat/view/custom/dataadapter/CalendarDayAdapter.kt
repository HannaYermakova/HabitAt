package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.useCase.CalendarDay
import by.aermakova.habitat.model.utilenums.Weekday
import by.aermakova.habitat.view.custom.CalendarDayView

class CalendarDayAdapter : RecyclerView.Adapter<CalendarDayAdapter.CalendarDayHolder>() {
    private val calendarDays: Array<CalendarDay> = generateWeek()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDayHolder {
        return CalendarDayHolder(CalendarDayView(parent.context))
    }

    override fun getItemCount(): Int {
        return calendarDays.size
    }

    override fun onBindViewHolder(holder: CalendarDayHolder, position: Int) {
        val view = holder.view
        view.setCalendarDay(calendarDays[position])
    }

    class CalendarDayHolder(val view: CalendarDayView) : RecyclerView.ViewHolder(view)

    private fun generateWeek(): Array<CalendarDay> {
        val weekDays: Array<Weekday> = Weekday.week
        val calendarDays: Array<CalendarDay> =
         Array(7) { i ->
            CalendarDay(weekDays[i], hasHabits = true, isToday = true, dayNumber = 15)
        }
        return calendarDays
}
}
