package by.aermakova.habitat.model.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.HabitModel
import by.aermakova.habitat.model.model.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ObserveHabitUseCase(
    habitAdapter: ListAdapter<HabitModel, out RecyclerView.ViewHolder>,
    private val dataBase: AppDataBase
) : ObserveUseCase<HabitModel>(habitAdapter) {

    private val list = MutableLiveData<List<HabitModel>>()

    fun getHabitsOfCategoryList(scope: CoroutineScope, categoryId: Long) {
        scope.launch {
            list.value = withContext(Dispatchers.IO) {
                dataBase.habitDao().getHabitsByCategoryId(categoryId).map { it.toModel() }
            }
        }
    }

    fun getHabitsList(scope: CoroutineScope) {
        scope.launch {
            list.value = withContext(Dispatchers.IO) {
                dataBase.habitDao().getAllHabits().map { it.toModel() }
            }
        }
    }

    override fun getList(): LiveData<List<HabitModel>> {
        return list
    }
}