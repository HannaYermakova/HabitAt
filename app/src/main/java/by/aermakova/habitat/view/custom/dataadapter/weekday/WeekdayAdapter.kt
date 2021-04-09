package by.aermakova.habitat.view.custom.dataadapter.weekday

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.CustomToggleDayOfWeekDataBinding
import by.aermakova.habitat.model.model.WeekdayWrapper

class WeekdayAdapter :
    ListAdapter<WeekdayWrapper,
            WeekdayAdapter.WeekDayToggleHolder>(WeekdayDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDayToggleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CustomToggleDayOfWeekDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.custom_toggle_day_of_week_data,
            parent,
            false
        )
        return WeekDayToggleHolder(binding)
    }

    override fun onBindViewHolder(holder: WeekDayToggleHolder, position: Int) {
        holder.binding.weekday = getItem(position)
    }

    class WeekDayToggleHolder(val binding: CustomToggleDayOfWeekDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}