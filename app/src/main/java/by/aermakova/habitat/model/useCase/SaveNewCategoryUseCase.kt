package by.aermakova.habitat.model.useCase

import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.CategoryModel
import by.aermakova.habitat.model.model.toEntity
import by.aermakova.habitat.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SaveNewCategoryUseCase(
    private val dataBase: AppDataBase
) {

    val saveCategoryCommand: SingleLiveEvent<Long> = SingleLiveEvent()

    fun saveCategory(
        category: CategoryModel?,
        scope: CoroutineScope,
        errorAction: () -> Unit
    ) {
        category?.let {
            scope.launch {
                try {
                    saveCategoryCommand.value = withContext(Dispatchers.IO) {
                        dataBase.categoryDao().insert(category.toEntity())
                    }
                    saveCategoryCommand.call()
                } catch (e: Exception) {
                    e.printStackTrace()
                    errorAction.invoke()
                }

            }
        }
    }
}