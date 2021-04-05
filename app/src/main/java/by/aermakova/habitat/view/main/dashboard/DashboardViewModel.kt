package by.aermakova.habitat.view.main.dashboard

import androidx.lifecycle.LiveData
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.useCase.GetListOfHabitsUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import by.aermakova.habitat.view.custom.dataadapter.CategoryAdapter
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val categoryNavigation: CategoryNavigation,
    private val habitNavigation: HabitNavigation,
    private val dataBase: AppDataBase,
    val userName: String,
    val listOfHabits: GetListOfHabitsUseCase
) : BaseViewModel() {

    val addNewCategory = { categoryNavigation.navigateToAddNewElementFragment() }

    val navigateFunction = { habitNavigation.navigateToAddNewElementFragment() }

    val categoryAdapter = CategoryAdapter(categoryNavigation)

    val categories: LiveData<List<Category>>
        get() = dataBase.categoryDao().getAllCategory()

    init {
        listOfHabits.get(disposable)
    }

    fun openNotificationFragment() {}

    fun updateCategories(categories: List<Category>) {
        categoryAdapter.submitList(categories)
    }
}