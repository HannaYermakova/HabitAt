package by.aermakova.habitat.view.main.calendar

import by.aermakova.habitat.model.useCase.calendar.ShowWeekCalendarUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import javax.inject.Inject


class CalendarViewModel @Inject constructor(
    val showWeekCalendarUseCase: ShowWeekCalendarUseCase
) : BaseViewModel() {

}