package by.aermakova.habitat.view.main.category.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.useCase.ObserveUseCase
import javax.inject.Inject

class CategoryItemViewModel @Inject constructor(
    val habitObserver: ObserveUseCase<Habit>,
    private val categoryId: Long,
    private val dataBase: AppDataBase,
    private val router: CategoryItemNavigation
) : ViewModel() {

    val category: LiveData<Category>
        get() = dataBase.categoryDao().getById(categoryId)

    val addHabit = { router.navigateToAddNewElementFragment(categoryId) }

    val back = { router.popBack() }
}