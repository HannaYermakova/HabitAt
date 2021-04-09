package by.aermakova.habitat.view.main.category.addNew

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aermakova.habitat.model.model.CategoryModel
import by.aermakova.habitat.model.useCase.SaveNewCategoryUseCase
import by.aermakova.habitat.model.useCase.SelectColorUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import by.aermakova.habitat.view.navigation.MainFlowNavigation
import javax.inject.Inject


class AddNewCategoryViewModel @Inject constructor(
    val selectColorUseCase: SelectColorUseCase,
    val saveNewCategoryUseCase: SaveNewCategoryUseCase,
    private val router: MainFlowNavigation
) : BaseViewModel() {

    private val _tempCategoryTitle = MutableLiveData<String>()
    val tempCategoryTitle: MutableLiveData<String>
        get() = _tempCategoryTitle

    val saveCategory = {
        saveNewCategoryUseCase.saveCategory(generateCategory(), viewModelScope) {
            showError()
        }
    }

    private fun generateCategory(): CategoryModel? {
        return if (TextUtils.isEmpty(tempCategoryTitle.value)
            || (selectColorUseCase.selectedColor == null)
        ) {
            showEmptyFieldsError()
            null
        } else {
            val category = CategoryModel(
                title = _tempCategoryTitle.value!!,
                count = 0,
                color = selectColorUseCase.selectedColor!!.cardColor.id
            )
            category
        }
    }

    val back = { router.popBack() }

    fun loadColors() {
        selectColorUseCase.loadColors(viewModelScope)
    }
}