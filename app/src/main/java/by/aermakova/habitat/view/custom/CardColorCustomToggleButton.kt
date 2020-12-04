package by.aermakova.habitat.view.custom

import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import by.aermakova.habitat.databinding.CustomToggleCardColorBinding
import by.aermakova.habitat.model.utilenums.CardColor
import by.aermakova.habitat.util.UiUtils

class CardColorCustomToggleButton(context: Context) : RelativeLayout(context) {
    private var mBinding: CustomToggleCardColorBinding
    private var mContext: Context
    var color: CardColor? = null
        set(value) {
            field = value
            mBinding.toggle.background = color?.let { generateSelector(it) }
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = CustomToggleCardColorBinding.inflate(inflater, this, true)
        mContext = context
    }

    override fun isSelected(): Boolean {
        return mBinding.toggle.isChecked
    }

    override fun setSelected(v: Boolean) {
        mBinding.toggle.isChecked = v
    }

    fun setOnCheckListener(listener: (CompoundButton?, Boolean) -> Unit) {
        mBinding.toggle.setOnCheckedChangeListener(listener)
    }

    private fun generateSelector(color: CardColor): Drawable {
        val drawable = StateListDrawable()
        with(drawable) {
            addState(intArrayOf(R.attr.state_checked), generateCircleDrawable(color.cardColorId, color.textColorId))
            addState(intArrayOf(R.attr.state_enabled), generateCircleDrawable(color.cardColorId, 0))
        }
        return drawable
    }

    private fun generateCircleDrawable(backgroundColor: Int, borderColor: Int): Drawable {
        val gradientDrawable = GradientDrawable()
        with(gradientDrawable) {
            shape = GradientDrawable.OVAL
            cornerRadius = UiUtils.RADIUS.toFloat()
            setColor(ContextCompat.getColor(mContext, backgroundColor))
            if (borderColor > 0) setStroke(UiUtils.STROKE_WIDTH, ContextCompat.getColor(mContext, borderColor)) else setStroke(UiUtils.STROKE_WIDTH, Color.TRANSPARENT)
        }
        return gradientDrawable
    }
}