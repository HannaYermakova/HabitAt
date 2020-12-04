package by.aermakova.habitat.view.custom

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import by.aermakova.habitat.databinding.HabitCustomAdditionalCardBinding

class HabitCardAdditionalView(context: Context) : LinearLayout(context) {
    private var mBinding: HabitCustomAdditionalCardBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = HabitCustomAdditionalCardBinding.inflate(inflater, this, true)
        setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

    fun setTextVisible(visible: Boolean) {
        mBinding.descriptionText.visibility = if (visible) View.VISIBLE else View.GONE
    }
}