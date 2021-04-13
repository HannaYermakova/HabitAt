package by.aermakova.habitat.model.useCase.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.CalendarDay
import by.aermakova.habitat.model.model.habit.HabitModel
import by.aermakova.habitat.model.model.habit.toModel
import by.aermakova.habitat.util.Constants.WEEK_SIZE
import by.aermakova.habitat.view.custom.dataadapter.calendar.CalendarDayAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class ShowWeekCalendarUseCase(
    val dataBase: AppDataBase
) {

    val adapter = CalendarDayAdapter()

    fun generateWeek(scope: CoroutineScope): LiveData<Array<CalendarDay>> {
        val liveData = MutableLiveData<Array<CalendarDay>>()
        scope.launch {
            val habits = getHabits()
            val week = CalendarConverter.generateWeek()
            val list = Array(WEEK_SIZE) { i ->
                with(week[i]) {
                    CalendarDay(
                        weekday,
                        number,
                        isToday,
                        dayHasInfo(habits[i])
                    )
                }
            }
            liveData.value = list
        }
        return liveData
    }

    private fun dayHasInfo(arrayList: List<HabitModel>): Boolean {
        return !arrayList.isNullOrEmpty()
    }

    private suspend fun getHabits(): Array<ArrayList<HabitModel>> {
        val arrayList = Array<ArrayList<HabitModel>>(7) { arrayListOf() }
        val habits = dataBase.habitDao().getAllHabits()
        for (i in habits.indices) {
            val week = habits[i].weekDays
            for (j in week.indices) {
                if (week[j]) {
                    arrayList[j].add(habits[i].toModel())
                }
            }
        }
        return arrayList
    }
}