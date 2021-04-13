package by.aermakova.habitat.model.useCase.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.habit.HabitModel
import by.aermakova.habitat.model.model.habit.toModel
import by.aermakova.habitat.view.custom.dataadapter.habit.HabitWeekAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ShowHabitsWeekUseCase(
    private val dataBase: AppDataBase
) {

    val adapter = HabitWeekAdapter()

    private val _habits = MutableLiveData<List<HabitModel>>()
    val habits: LiveData<List<HabitModel>>
        get() = _habits

    fun getHabits(scope: CoroutineScope) {
        scope.launch {
            _habits.value = dataBase.habitDao().getAllHabits().map { it.toModel() }
        }
    }

    fun updateHabitsList(habits: List<HabitModel>) {
        adapter.submitList(habits)
    }
}