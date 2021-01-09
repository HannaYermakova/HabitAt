package by.aermakova.habitat.view.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
        private val dataBase: AppDataBase,
        val userName: String
) : ViewModel() {

    val habits: LiveData<List<Habit?>?>?
        get() = dataBase.habitDao().all

    val categories: LiveData<List<Category?>?>?
        get() = dataBase.categoryDao().all

    fun openNotificationFragment() {}
}