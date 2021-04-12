package by.aermakova.habitat.view.custom.dataadapter.calendar

import androidx.recyclerview.widget.DiffUtil
import by.aermakova.habitat.model.model.CalendarDay


object CalendarDayDiffCallback: DiffUtil.ItemCallback<CalendarDay>() {

    override fun areItemsTheSame(oldItem: CalendarDay, newItem: CalendarDay): Boolean {
        return oldItem.weekday == newItem.weekday
    }

    override fun areContentsTheSame(oldItem: CalendarDay, newItem: CalendarDay): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}