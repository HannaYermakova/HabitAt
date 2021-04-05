package by.aermakova.habitat.view.main.habit

import android.text.TextUtils
import androidx.lifecycle.LiveData
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.util.SingleLiveEvent
import by.aermakova.habitat.view.base.BaseViewModel
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class AddNewHabitViewModel @Inject constructor(
        private val dataBase: AppDataBase
): BaseViewModel() {

    @JvmField
    var title: String? = null
    private var day = 21
    private var categoryId: Long = 0
    private var weekDays = BooleanArray(7)
    private val tempWeekDays = BooleanArray(7)
    @JvmField
    var notificationEnable = false
    private var hours = 0
    private var minutes = 0
    val saveHabitCommand: SingleLiveEvent<Void> = SingleLiveEvent()
    val showErrorMessageCommand: SingleLiveEvent<Int> = SingleLiveEvent()
    val setNotificationLogicCommand: SingleLiveEvent<Habit> = SingleLiveEvent()


    fun saveHabit() {
        if (TextUtils.isEmpty(title) || categoryId == 0L) {
            showErrorMessageCommand.setValue(R.string.error_empty_fields)
        } else {
            val startTime = System.currentTimeMillis()
            val habit = Habit(title, day, startTime, categoryId, weekDays, notificationEnable, 0)
            if (notificationEnable) {
                habit.setNotificationTime(hours, minutes)
            }
            Completable.fromAction {
                dataBase.habitDao().insert(habit)
                val category = dataBase.categoryDao().getById(categoryId)
                category?.updateCount(1)
                dataBase.categoryDao().update(category)
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

    fun setTime(hours: Int, minutes: Int) {
        this.hours = hours
        this.minutes = minutes
    }

    fun setDaysCount(progress: Int) {
        day = progress
    }

    fun setWeekDays(weekDays: BooleanArray) {
        this.weekDays = weekDays
    }

    fun setEveryDay() {
        System.arraycopy(weekDays, 0, tempWeekDays, 0, tempWeekDays.size)
        Arrays.fill(weekDays, true)
    }

    val allCategories: LiveData<List<Category>>
        get() = dataBase.categoryDao().getAllCategory()

    fun setSelectedWeekDays() {
        weekDays = tempWeekDays
    }

    fun setNotificationEnable(isChecked: Boolean) {
        notificationEnable = isChecked
    }

}