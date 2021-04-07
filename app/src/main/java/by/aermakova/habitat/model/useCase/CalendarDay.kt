package by.aermakova.habitat.model.useCase

import by.aermakova.habitat.model.utilenums.Weekday

class CalendarDay(var weekDay: Weekday,
                  val hasHabits: Boolean,
                  val isToday: Boolean,
                  val dayNumber: Int)