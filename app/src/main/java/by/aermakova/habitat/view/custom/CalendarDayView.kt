package by.aermakova.habitat.view.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import by.aermakova.habitat.databinding.CustomCalendarDayBinding
import by.aermakova.habitat.model.useCase.CalendarDay

class CalendarDayView(context: Context) : LinearLayout(context) {
    private var mBinding: CustomCalendarDayBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = CustomCalendarDayBinding.inflate(inflater, this, true)
    }

    fun setCalendarDay(calendarDay: CalendarDay) {
        with(mBinding) {
            weekDayTitle.text = calendarDay.weekDay.day
            today.visibility = if (calendarDay.isToday) View.VISIBLE else View.INVISIBLE
            habits.visibility = if (calendarDay.hasHabits) View.VISIBLE else View.INVISIBLE
            dayNumber.text = calendarDay.dayNumber.toString()
        }
    }
}
