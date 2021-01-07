package by.aermakova.habitat.view.main.category.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.app.App
import javax.inject.Inject

class CategoryItemViewModel @Inject constructor(
        private val dataBase: AppDataBase
) : ViewModel() {

    private val categoryId: Long = 0
    val habitsList: LiveData<List<Habit?>?>?
        get() = dataBase.habitDao().getHabitsByCategoryId(categoryId)
}