package by.aermakova.habitat.view.main.category.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.model.CategoryModel
import by.aermakova.habitat.model.model.toModel
import by.aermakova.habitat.model.useCase.ObserveUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import javax.inject.Inject

class CategoryItemViewModel @Inject constructor(
    val habitObserver: ObserveUseCase<Habit>,
    private val categoryId: Long,
    private val dataBase: AppDataBase,
    private val router: CategoryItemNavigation
) : BaseViewModel() {

    val category: LiveData<CategoryModel>
        get() = map(dataBase.categoryDao().getById(categoryId)) { it.toModel() }

    val addHabit = { router.navigateToAddNewElementFragment(categoryId) }

    val back = { router.popBack() }
}