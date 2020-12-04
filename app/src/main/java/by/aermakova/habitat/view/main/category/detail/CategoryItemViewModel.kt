package by.aermakova.habitat.view.main.category.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.app.App

class CategoryItemViewModel(private val categoryId: Long) : ViewModel() {
    private val mDataBase: AppDataBase = App.component!!.dataBase
    val habitsList: LiveData<List<Habit?>?>?
        get() = mDataBase.habitDao().getHabitsByCategoryId(categoryId)
}