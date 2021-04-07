package by.aermakova.habitat.model.model

import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.db.entity.Category

data class CategoryWrapper(
    val category: Category,
    val isSelected: MutableLiveData<Boolean>
)