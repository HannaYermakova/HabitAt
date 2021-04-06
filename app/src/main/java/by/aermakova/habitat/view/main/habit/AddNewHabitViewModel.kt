package by.aermakova.habitat.view.main.habit

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.util.SingleLiveEvent
import by.aermakova.habitat.view.base.BaseViewModel
import by.aermakova.habitat.view.custom.dialog.TimePickerNavigation
import by.aermakova.habitat.view.custom.weekdaysStrategy.WeekdaysStrategy
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class AddNewHabitViewModel @Inject constructor(
    private val dataBase: AppDataBase,
    private val timePickerNavigation: TimePickerNavigation
) : BaseViewModel() {

    @JvmField
    var title: String? = null

    private var categoryId: Long = 0

    private var weekDays = BooleanArray(7)
    private val tempWeekDays = BooleanArray(7)

    val saveHabitCommand: SingleLiveEvent<Void> = SingleLiveEvent()
    val showErrorMessageCommand: SingleLiveEvent<Int> = SingleLiveEvent()
    val setNotificationLogicCommand: SingleLiveEvent<Habit> = SingleLiveEvent()

    private val _notificationEnable = MutableLiveData(false)
    val notificationEnable: MutableLiveData<Boolean>
        get() = _notificationEnable

    private val _durationInDays = MutableLiveData(21)
    val durationInDays: MutableLiveData<Int>
        get() = _durationInDays

    val allCategories: LiveData<List<Category>>
        get() = dataBase.categoryDao().getAllCategory()

    val openTimePicker = { timePickerNavigation.openItemDialog() }

    val selectedTime = timePickerNavigation.getDialogResult()

    val weekdaysRecyclerVisibility = MutableLiveData<Boolean>(false)

    val selectWeekdaysStrategy: (Int) -> Unit = { position ->
        when (position) {
            WeekdaysStrategy.EVERYDAY.position -> {
                weekdaysRecyclerVisibility.value = false
                setEveryDay()
            }
            WeekdaysStrategy.CHOOSE_MANUALLY.position -> {
                weekdaysRecyclerVisibility.value = true
                setSelectedWeekDays()
            }
        }
    }

    fun saveHabit() {
        if (TextUtils.isEmpty(title) || categoryId == 0L) {
            showErrorMessageCommand.setValue(R.string.error_empty_fields)
        } else {
            val startTime = System.currentTimeMillis()
            val habit = Habit(
                title,
                _durationInDays.value!!,
                startTime,
                categoryId,
                weekDays,
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

    fun setCategoryId(categoryId: Long) {
        this.categoryId = categoryId
    }

    fun setWeekDays(weekDays: BooleanArray) {
        this.weekDays = weekDays
    }

    fun setEveryDay() {
        System.arraycopy(weekDays, 0, tempWeekDays, 0, tempWeekDays.size)
        Arrays.fill(weekDays, true)
    }

    fun setSelectedWeekDays() {
        weekDays = tempWeekDays
    }
}