package by.aermakova.habitat.view.main.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import by.aermakova.habitat.model.model.CalendarDay
import by.aermakova.habitat.model.useCase.calendar.ShowHabitsWeekUseCase
import by.aermakova.habitat.model.useCase.calendar.ShowWeekCalendarUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import javax.inject.Inject


class CalendarViewModel @Inject constructor(
    val showWeekCalendarUseCase: ShowWeekCalendarUseCase,
    val showHabitsWeekUseCase: ShowHabitsWeekUseCase
) : BaseViewModel() {

    val calendar: LiveData<Array<CalendarDay>>
        get() = showWeekCalendarUseCase.generateWeek(viewModelScope)

    fun loadHabitsList() {
        showHabitsWeekUseCase.getHabits(viewModelScope)
    }
}