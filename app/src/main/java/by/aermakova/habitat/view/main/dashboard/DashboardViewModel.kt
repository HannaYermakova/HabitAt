package by.aermakova.habitat.view.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.app.App

class DashboardViewModel : ViewModel() {
    @kotlin.jvm.JvmField
    var mUserName: String? = null
    private val mDataBase: AppDataBase = App.component!!.dataBase
    val habits: LiveData<List<Habit?>?>?
        get() = mDataBase.habitDao().all

    val categories: LiveData<List<Category?>?>?
        get() = mDataBase.categoryDao().all

    fun setUser(name: String?) {
        mUserName = name
    }

    fun openNotificationFragment() {}
}