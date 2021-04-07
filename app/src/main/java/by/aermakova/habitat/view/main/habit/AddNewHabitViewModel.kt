package by.aermakova.habitat.view.main.habit

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.useCase.SelectCategoryUseCase
import by.aermakova.habitat.model.useCase.SelectWeekdaysUseCase
import by.aermakova.habitat.util.Constants.HABIT_DURATION_INITIAL
import by.aermakova.habitat.util.SingleLiveEvent
import by.aermakova.habitat.view.base.BaseViewModel
import by.aermakova.habitat.view.custom.dialog.TimePickerNavigation
import by.aermakova.habitat.view.custom.weekdaysStrategy.WeekdaysStrategy
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AddNewHabitViewModel @Inject constructor(
    val selectWeekdaysUseCase: SelectWeekdaysUseCase,
    val selectCategoryUseCase: SelectCategoryUseCase,
    private val timePickerNavigation: TimePickerNavigation,
    private val router: AddNewHabitNavigation
) : BaseViewModel() {

    private var categoryId: Long = 0

    val saveHabitCommand: SingleLiveEvent<Void> = SingleLiveEvent()
    val showErrorMessageCommand: SingleLiveEvent<Int> = SingleLiveEvent()
    val setNotificationLogicCommand: SingleLiveEvent<Habit> = SingleLiveEvent()

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

    fun saveHabit() {
        if (TextUtils.isEmpty(tempHabitTitle.value) || categoryId == 0L) {
            showErrorMessageCommand.setValue(R.string.error_empty_fields)
        } else {
            val startTime = System.currentTimeMillis()
            val habit = Habit(
                tempHabitTitle.value,
                _durationInDays.value!!,
                startTime,
                categoryId,
                selectWeekdaysUseCase.getSelected(),
                notificationEnable.value!!,
                0
            )
            if (notificationEnable.value!! && selectedTime.value != null) {
                habit.setNotificationTime(selectedTime.value!!)
            }
            Completable.fromAction {
                /*dataBase.habitDao().insert(habit)
                val category = dataBase.categoryDao().getById(categoryId)
                category?.updateCount(1)
                dataBase.categoryDao().update(category)*/
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onComplete() {
                        saveHabitCommand.call()
                        setNotificationLogicCommand.value = habit
                    }

                    override fun onError(e: Throwable) {
                        showErrorMessageCommand.value = R.string.error_db
                    }
                })
        }
    }

    val back = { router.popBack() }
}