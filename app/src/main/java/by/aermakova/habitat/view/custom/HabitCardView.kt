package by.aermakova.habitat.view.custom

import android.content.Context
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.HabitCustomCardBinding
import by.aermakova.habitat.model.model.HabitModel
import by.aermakova.habitat.model.useCase.HabitLogic

class HabitCardView(context: Context) : CardView(context) {
    private var mBinding: HabitCustomCardBinding
    private var mContext: Context = context

    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.habit_custom_card, this, true)
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

    fun setHabitItem(habit: HabitModel?) {
        mBinding.habitItem = habit
        mBinding.habitLogic = HabitLogic(habit, mContext)
    }
}