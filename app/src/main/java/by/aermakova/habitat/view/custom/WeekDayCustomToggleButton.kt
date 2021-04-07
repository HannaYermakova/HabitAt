package by.aermakova.habitat.view.custom

import android.content.Context
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.RelativeLayout
import by.aermakova.habitat.databinding.CustomToggleDayOfWeekBinding
import by.aermakova.habitat.model.utilenums.Weekday

class WeekDayCustomToggleButton(context: Context) : RelativeLayout(context) {
    private var mBinding: CustomToggleDayOfWeekBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = CustomToggleDayOfWeekBinding.inflate(inflater, this, true)
    }

    fun setOnCheckListener(listener: (CompoundButton?, Boolean) -> Unit) {
        mBinding.toggle.setOnCheckedChangeListener(listener)
    }

    override fun setSelected(v: Boolean) {
        mBinding.toggle.isChecked = v
    }

    fun setWeekDay(weekDay: Weekday) {
        with(mBinding.toggle) {
            text = weekDay.day
            textOn = weekDay.day
            textOff = weekDay.day
        }
    }
}