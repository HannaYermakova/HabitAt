package by.aermakova.habitat.model.model

import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.utilenums.Weekday

class WeekdayWrapper(val weekday: Weekday, val isSelected: MutableLiveData<Boolean>)