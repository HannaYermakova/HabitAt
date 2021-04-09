package by.aermakova.habitat.model.utilenums

import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.R

enum class Weekday(val dayTitleId: Int, val number: Int) {
    MONDAY(R.string.week_day_monday, 0),
    TUESDAY(R.string.week_day_tuesday, 1),
    WEDNESDAY(R.string.week_day_wednesday, 2),
    THURSDAY(R.string.week_day_thursday, 3),
    FRIDAY(R.string.week_day_friday, 4),
    SATURDAY(R.string.week_day_saturday, 5),
    SUNDAY(R.string.week_day_sunday, 6);

    companion object {
        val week: Array<Weekday> = values()
    }
}

class WeekdayWrapper(val weekday: Weekday, val isSelected: MutableLiveData<Boolean>)