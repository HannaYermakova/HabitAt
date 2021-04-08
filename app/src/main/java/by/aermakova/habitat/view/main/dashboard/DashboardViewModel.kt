package by.aermakova.habitat.view.main.dashboard

import androidx.lifecycle.viewModelScope
import by.aermakova.habitat.model.model.CategoryModel
import by.aermakova.habitat.model.useCase.ObserveHabitUseCase
import by.aermakova.habitat.model.useCase.ObserveUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    val habitsObserver: ObserveHabitUseCase,
    val categoryObserver: ObserveUseCase<CategoryModel>,
    val userName: String,
    private val categoryNavigation: CategoryNavigation
) : BaseViewModel() {

    val addNewCategory = { categoryNavigation.navigateToAddNewElementFragment() }

    init {
        habitsObserver.getHabitsList(viewModelScope)
    }

    fun openNotificationFragment() {}
}