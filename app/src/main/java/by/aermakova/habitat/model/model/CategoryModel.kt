package by.aermakova.habitat.model.model

import by.aermakova.habitat.model.db.entity.Category

data class CategoryModel(
    val id: Long,
    val title: String,
    val count: Int,
    val color: Int
)

fun Category.toModel(): CategoryModel {
    return CategoryModel(id, title?: "", count, color)
}