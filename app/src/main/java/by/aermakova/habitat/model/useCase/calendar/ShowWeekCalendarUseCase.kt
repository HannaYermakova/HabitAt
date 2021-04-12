package by.aermakova.habitat.model.useCase.calendar

import by.aermakova.habitat.model.model.CalendarDay
import by.aermakova.habitat.view.custom.dataadapter.calendar.CalendarDayAdapter

class ShowWeekCalendarUseCase(

) {

    val adapter = CalendarDayAdapter()

    fun generateWeek(): List<CalendarDay> {
        return CalendarConverter.generateWeek()
            .map { CalendarDay(it.weekday, it.number, it.isToday, false) }
    }
}