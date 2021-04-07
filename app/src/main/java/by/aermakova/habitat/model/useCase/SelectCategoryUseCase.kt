package by.aermakova.habitat.model.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.CategoryWrapper
import by.aermakova.habitat.view.custom.dataadapter.CategoryPillsAdapterWrapper

class SelectCategoryUseCase(
    private val dataBase: AppDataBase,
    openCreateCategory: () -> Unit
) {

    private val update : (CategoryWrapper) -> Unit = {

    }

    val categoriesAdapter = CategoryPillsAdapterWrapper(update, openCreateCategory)

//    private val categoryWrappers = MutableList<CategoryWrapper>()

    val allCategories: LiveData<List<CategoryWrapper>>
        get() = map(dataBase.categoryDao().getAllCategory()){it.map { CategoryWrapper(it, false)}}

    fun setCategories(categories : List<CategoryWrapper>){
        categoriesAdapter.submitList(categories)
    }
}