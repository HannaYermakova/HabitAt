package by.aermakova.habitat.view.custom

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.CustomCategoryCardBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.useCase.CategoryLogic
import by.aermakova.habitat.model.utilenums.CardColor

class CategoryHabitCardView(context: Context) : CardView(context) {
    private var mBinding: CustomCategoryCardBinding
    private var mContext: Context = context

    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.custom_category_card, this, true)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        elevation = 0f
    }

    fun setCategory(category: Category?) {
        mBinding.category = category
        val color = CardColor.getColorById(category!!.color)
        val categoryLogic = CategoryLogic(category, mContext)
        mBinding.cardBack.setBackgroundColor(ContextCompat.getColor(mContext, color.cardColorId))
        mBinding.cardText.setTextColor(ContextCompat.getColor(mContext, color.textColorId))
        mBinding.habitCounter.text = categoryLogic.stringCount
    }
}