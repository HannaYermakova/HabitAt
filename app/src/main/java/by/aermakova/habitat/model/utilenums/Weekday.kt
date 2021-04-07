package by.aermakova.habitat.model.utilenums

import androidx.lifecycle.MutableLiveData

enum class Weekday(val day: String, val number: Int) {
    MONDAY("Пн", 0),
    TUESDAY("Вт", 1),
    WEDNESDAY("Ср", 2),
    THURSDAY("Чт", 3),
    FRIDAY("Пт", 4),
    SATURDAY("Сб", 5),
    SUNDAY("Вс", 6);

    companion object {
        val week: Array<Weekday> = values()
    }
}

class WeekdayWrapper(val weekday: Weekday, val isSelected: MutableLiveData<Boolean>)