package by.aermakova.habitat.view.custom.dataadapter.habit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.HabitCalendarBinding
import by.aermakova.habitat.model.model.habit.HabitModel


class HabitWeekAdapter :
    ListAdapter<HabitModel, HabitWeekAdapter.HabitViewHolder>(HabitDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: HabitCalendarBinding =
            DataBindingUtil.inflate(inflater, R.layout.habit_calendar, parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.binding.week = getItem(position)
    }

    class HabitViewHolder(val binding: HabitCalendarBinding) : RecyclerView.ViewHolder(binding.root)
}