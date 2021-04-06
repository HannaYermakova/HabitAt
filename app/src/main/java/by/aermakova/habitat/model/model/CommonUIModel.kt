package by.aermakova.habitat.model.model

import by.aermakova.habitat.util.FunctionLong

abstract class CommonUIModel(
    val id: Long,
    val layout: Int,
    val variableId: Int,
    val clickAction: FunctionLong? = null,
    val longClickAction: FunctionLong? = null
)