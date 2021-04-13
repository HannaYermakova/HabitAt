package by.aermakova.habitat.view.main.habit

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aermakova.habitat.model.model.habit.HabitModel
import by.aermakova.habitat.model.useCase.HabitAlarmUseCase
import by.aermakova.habitat.model.useCase.SaveNewHabitUseCase
import by.aermakova.habitat.model.useCase.SelectCategoryUseCase
import by.aermakova.habitat.model.useCase.SelectWeekdaysUseCase
import by.aermakova.habitat.util.Constants.HABIT_DURATION_INITIAL
import by.aermakova.habitat.view.base.BaseViewModel
import by.aermakova.habitat.view.custom.dialog.TimePickerNavigation
import by.aermakova.habitat.model.useCase.weekdaysStrategy.WeekdaysStrategy
import javax.inject.Inject


class AddNewHabitViewModel @Inject constructor(
    val selectWeekdaysUseCase: SelectWeekdaysUseCase,
    val selectCategoryUseCase: SelectCategoryUseCase,
    val saveNewHabitUseCase: SaveNewHabitUseCase,
    private val habitAlarmUseCase: HabitAlarmUseCase,
    private val timePickerNavigation: TimePickerNavigation,
    private val router: AddNewHabitNavigation
    ) : BaseViewModel() {

    private val _tempHabitTitle = MutableLiveData<String>()
    val tempHabitTitle: MutableLiveData<String>
        get() = _tempHabitTitle

    private val _notificationEnable = MutableLiveData(false)
    val notificationEnable: MutableLiveData<Boolean>
        get() = _notificationEnable

    private val _durationInDays = MutableLiveData(HABIT_DURATION_INITIAL)
    val durationInDays: MutableLiveData<Int>
        get() = _durationInDays

    val openTimePicker = { timePickerNavigation.openItemDialog() }

    val selectedTime = timePickerNavigation.getDialogResult()

    val weekdaysRecyclerVisibility = MutableLiveData<Boolean>(false)

    val selectWeekdaysStrategy: (Int) -> Unit = { position ->
        when (position) {
            WeekdaysStrategy.EVERYDAY.position -> {
                weekdaysRecyclerVisibility.value = false
                selectWeekdaysUseCase.setEveryDay()
            }
            WeekdaysStrategy.CHOOSE_MANUALLY.position -> {
                weekdaysRecyclerVisibility.value = true
                selectWeekdaysUseCase.setSelectedWeekDays()
            }
        }
    }

    private fun generateHabit(): HabitModel? {
        return if (TextUtils.isEmpty(tempHabitTitle.value)
            || (selectCategoryUseCase.selectedCategory == null)
        ) {
            showEmptyFieldsError()
            null
        } else {
            val startTime = System.currentTimeMillis()
            val habit = HabitModel(
                title = _tempHabitTitle.value!!,
                day = _durationInDays.value!!,
                startTime = startTime,
                categoryId = 1,
                weekDays = selectWeekdaysUseCase.getSelected(),
                isNotificationEnable = notificationEnable.value!!,
                hours = selectedTime.value?.hours?: 0,
                minute = selectedTime.value?.minutes?:0,
                markedDays = 0
            )
            habit
        }
    }

    val saveHabit = {
        saveNewHabitUseCase.saveHabit(generateHabit(), viewModelScope) {
            showError()
        }
    }

    private fun createHabitAlarm(habit: HabitModel) {
        habitAlarmUseCase.setAllHabitAlarms(habit)
    }

    fun loadCategories() {
        selectCategoryUseCase.loadCategories(viewModelScope)
    }

    fun setAlarmAndClose(habit: HabitModel?) {
        habit?.let { createHabitAlarm(habit) }
        back.invoke()
    }

    val back = { router.popBack() }
}