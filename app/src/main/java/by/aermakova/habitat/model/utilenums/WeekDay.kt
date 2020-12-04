package by.aermakova.habitat.model.utilenums

enum class WeekDay(val day: String, val number: Int) {
    MONDAY("Пн", 0),
    TUESDAY("Вт", 1),
    WEDNESDAY("Ср", 2),
    THURSDAY("Чт", 3),
    FRIDAY("Пт", 4),
    SATURDAY("Сб", 5),
    SUNDAY("Вс", 6);

    companion object {
        val week: Array<WeekDay> = values()
    }
}