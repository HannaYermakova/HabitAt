package by.aermakova.habitat.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import by.aermakova.habitat.databinding.CustomPillCreateFirstHabitBinding

class CategoryPillButtonView(context: Context) : RelativeLayout(context) {
    private var mBinding: CustomPillCreateFirstHabitBinding

    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        mBinding = CustomPillCreateFirstHabitBinding.inflate(inflater, this, true)
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        mBinding.categoryCreateFirst.setOnClickListener(listener)
    }
}