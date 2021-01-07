package by.aermakova.habitat.view.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.app.App
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
        private val dataBase: AppDataBase
) : ViewModel() {
    @kotlin.jvm.JvmField
    var mUserName: String? = null

    val habits: LiveData<List<Habit?>?>?
        get() = dataBase.habitDao().all

    val categories: LiveData<List<Category?>?>?
        get() = dataBase.categoryDao().all

    fun setUser(name: String?) {
        mUserName = name
    }

    fun openNotificationFragment() {}
}