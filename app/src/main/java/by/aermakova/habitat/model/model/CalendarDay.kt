package by.aermakova.habitat.model.model

import by.aermakova.habitat.model.utilenums.Weekday

data class CalendarDay(
    val weekday: Weekday,
    val number: Int,
    val isToday: Boolean,
    val hasInfo: Boolean
)
