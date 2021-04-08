package by.aermakova.habitat.view.main.dashboard

import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.model.CategoryModel
import by.aermakova.habitat.model.useCase.ObserveUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    val habitsObserver: ObserveUseCase<Habit>,
    val categoryObserver: ObserveUseCase<CategoryModel>,
    val userName: String,
    private val categoryNavigation: CategoryNavigation
) : BaseViewModel() {

    val addNewCategory = { categoryNavigation.navigateToAddNewElementFragment() }

    fun openNotificationFragment() {}
}