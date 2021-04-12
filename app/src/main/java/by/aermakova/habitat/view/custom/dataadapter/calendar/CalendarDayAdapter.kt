package by.aermakova.habitat.view.custom.dataadapter.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.CustomCalendarDayDataBinding
import by.aermakova.habitat.model.model.CalendarDay

class CalendarDayAdapter :
    ListAdapter<CalendarDay, CalendarDayAdapter.CalendarDayHolder>(CalendarDayDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDayHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CustomCalendarDayDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.custom_calendar_day_data, parent, false)
        return CalendarDayHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarDayHolder, position: Int) {
       holder.binding.day = getItem(position)
    }

    class CalendarDayHolder(val binding: CustomCalendarDayDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}