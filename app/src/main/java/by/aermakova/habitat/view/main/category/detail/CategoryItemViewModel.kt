package by.aermakova.habitat.view.main.category.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.CategoryModel
import by.aermakova.habitat.model.model.toModel
import by.aermakova.habitat.model.useCase.ObserveHabitUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryItemViewModel @Inject constructor(
    val habitObserver: ObserveHabitUseCase,
    private val categoryId: Long,
    private val dataBase: AppDataBase,
    private val router: CategoryItemNavigation
) : BaseViewModel() {

    val category = MutableLiveData<CategoryModel>()

    init {
        viewModelScope.launch {
            category.value =
                withContext(Dispatchers.IO) { dataBase.categoryDao().getById(categoryId).toModel() }
        }
        habitObserver.getHabitsOfCategoryList(viewModelScope, categoryId)
    }

    val addHabit = { router.navigateToAddNewElementFragment(categoryId) }

    val back = { router.popBack() }
}