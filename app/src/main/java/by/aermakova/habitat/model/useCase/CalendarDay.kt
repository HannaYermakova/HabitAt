package by.aermakova.habitat.model.useCase

import by.aermakova.habitat.model.utilenums.WeekDay

class CalendarDay(var weekDay: WeekDay,
                  val hasHabits: Boolean,
                  val isToday: Boolean,
                  val dayNumber: Int)