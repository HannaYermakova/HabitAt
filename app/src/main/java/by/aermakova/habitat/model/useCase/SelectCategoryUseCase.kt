package by.aermakova.habitat.model.useCase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.CategoryWrapper
import by.aermakova.habitat.view.custom.dataadapter.CategoryPillsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectCategoryUseCase(
    private val dataBase: AppDataBase,
    openCreateCategory: () -> Unit
) {

    val categoriesAdapter = CategoryPillsAdapter({
        updateSelectedCategory(it)
    }, openCreateCategory)

    private var _selectedCategory: CategoryWrapper? = null
    val selectedCategory: CategoryWrapper?
        get()  = _selectedCategory

    private fun updateSelectedCategory(selectedCategory: CategoryWrapper) {
        this._selectedCategory = selectedCategory
        _allCategories.value?.let {
            it.map { wrapper ->
                wrapper.isSelected.value = wrapper.category == selectedCategory.category
            }
        }
    }

    fun loadCategories(scope: CoroutineScope) {
        scope.launch {
            _allCategories.value = withContext(Dispatchers.IO) {
                dataBase.categoryDao().getAllCategorySuspend().map {
                    CategoryWrapper(
                        it,
                        MutableLiveData(false)
                    )
                }
            }
        }
    }

    private val _allCategories = MutableLiveData<List<CategoryWrapper>>()
    val allCategories: LiveData<List<CategoryWrapper>>
        get() = _allCategories

    fun setCategories(categories: List<CategoryWrapper>) {
        categoriesAdapter.submitList(categories)
    }
}