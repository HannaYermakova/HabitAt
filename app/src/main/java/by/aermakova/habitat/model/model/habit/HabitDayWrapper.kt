package by.aermakova.habitat.model.model.habit

import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.utilenums.HabitDayState

data class HabitDayWrapper(
    val habit: HabitModel,
    val state: HabitDayState,
    val isChecked: MutableLiveData<Boolean>
)
