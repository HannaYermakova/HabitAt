package by.aermakova.habitat.model.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Habit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ObserveHabitUseCase(
    habitAdapter: ListAdapter<Habit, out RecyclerView.ViewHolder>,
    private val dataBase: AppDataBase
) : ObserveUseCase<Habit>(habitAdapter) {

    private val list = MutableLiveData<List<Habit>>()

    fun getHabitsOfCategoryList(scope: CoroutineScope, categoryId: Long) {
        scope.launch {
            list.value = withContext(Dispatchers.IO) {
                dataBase.habitDao().getHabitsByCategoryId(categoryId)
            }
        }
    }

    fun getHabitsList(scope: CoroutineScope) {
        scope.launch {
            list.value = withContext(Dispatchers.IO) {
                dataBase.habitDao().getAllHabits()
            }
        }
    }

    override fun getList(): LiveData<List<Habit>> {
        return list
    }
}