package by.aermakova.habitat.view.custom

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.CustomToggleHabitPillBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.utilenums.CardColor
import by.aermakova.habitat.util.UiUtils

class CategoryPillView : RelativeLayout {
    private var mBinding: CustomToggleHabitPillBinding? = null
    private var mContext: Context? = null
    var category: Category? = null
        set(value) {
            field = value
            mBinding!!.category = category
            val color: CardColor = CardColor.getColorById(category!!.color)
            mBinding!!.categoryToggle.background = generateSelector(color)
            mBinding!!.categoryToggle.setTextColor(ContextCompat.getColor(mContext!!, color.textColorId))
        }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.custom_toggle_habit_pill, this, true)
        mContext = context
    }

/*    fun setCategory(category: Category) {
        this.category = category
        mBinding!!.category = category
        val color: CardColor = CardColor.getColorById(category.color)
        mBinding!!.categoryToggle.background = generateSelector(color)
        mBinding!!.categoryToggle.setTextColor(ContextCompat.getColor(mContext!!, color.textColorId))
    }*/

    private fun generateSelector(color: CardColor): Drawable {
        val drawable = StateListDrawable()
        drawable.addState(intArrayOf(android.R.attr.state_checked), generatePillDrawable(color.cardColorId, color.textColorId))
        drawable.addState(intArrayOf(android.R.attr.state_enabled), generatePillDrawable(color.cardColorId, 0))
        return drawable
    }

    private fun generatePillDrawable(backgroundColor: Int, borderColor: Int): Drawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = UiUtils.CORNER_RADIUS.toFloat()
        shape.setColor(ContextCompat.getColor(mContext!!, backgroundColor))
        if (borderColor > 0) shape.setStroke(UiUtils.STROKE_WIDTH, ContextCompat.getColor(mContext!!, borderColor)) else shape.setStroke(UiUtils.STROKE_WIDTH, Color.TRANSPARENT)
        return shape
    }

    override fun isSelected(): Boolean {
        return mBinding!!.categoryToggle.isChecked
    }

    override fun setSelected(v: Boolean) {
        mBinding!!.categoryToggle.isChecked = v
    }

    override fun setEnabled(v: Boolean) {
        mBinding!!.categoryToggle.isEnabled = v
    }

    fun setOnCheckListener(listener: (CompoundButton?, Boolean) -> Unit) {
        mBinding!!.categoryToggle.setOnCheckedChangeListener(listener)
    }
}