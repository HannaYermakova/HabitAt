package by.aermakova.habitat.model.useCase

import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.utilenums.Weekday
import by.aermakova.habitat.model.model.WeekdayWrapper
import by.aermakova.habitat.util.Constants.WEEK_SIZE
import by.aermakova.habitat.view.custom.dataadapter.WeekdayAdapter
import by.aermakova.habitat.view.custom.weekdaysStrategy.WeekdaysStrategy

class SelectWeekdaysUseCase {

    val weekdayAdapter = WeekdayAdapter()
    private val week = MutableLiveData<List<WeekdayWrapper>>()
    private var strategySelected = WeekdaysStrategy.EVERYDAY

    fun setInitialWeek() {
        week.value = getWeekWrappers()
        weekdayAdapter.submitList(week.value)
    }

    private fun getWeekWrappers(): List<WeekdayWrapper> {
        return Weekday.values().map { WeekdayWrapper(it, MutableLiveData(false)) }
    }

    fun setEveryDay() {
        strategySelected = WeekdaysStrategy.EVERYDAY
    }

    fun setSelectedWeekDays() {
        strategySelected = WeekdaysStrategy.CHOOSE_MANUALLY
    }

    fun getSelected(): BooleanArray {
        return when (strategySelected) {
            WeekdaysStrategy.EVERYDAY -> BooleanArray(WEEK_SIZE) { true }
            WeekdaysStrategy.CHOOSE_MANUALLY -> convertWeekToBooleanArray()
        }
    }

    private fun convertWeekToBooleanArray(): BooleanArray {
       return BooleanArray(WEEK_SIZE){ i -> week.value?.let{it[i].isSelected.value?: false}?: false}
    }
}