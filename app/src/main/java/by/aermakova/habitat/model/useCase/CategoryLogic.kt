package by.aermakova.habitat.model.useCase

import android.content.Context
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.entity.Category

class CategoryLogic(private val category: Category?, private val context: Context?) : ObjectLogic() {
    val stringCount: String?
        get() {
            val resources = context!!.resources
            val nominative = resources.getString(R.string.nominative_habits)
            val genitive = resources.getString(R.string.genitive_habits)
            val accusative = resources.getString(R.string.accusative_habits)
            return getStringCount(category!!.count, nominative, genitive, accusative)
        }

}