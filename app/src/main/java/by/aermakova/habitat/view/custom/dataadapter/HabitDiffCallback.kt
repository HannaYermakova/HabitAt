package by.aermakova.habitat.view.custom.dataadapter

import androidx.recyclerview.widget.DiffUtil
import by.aermakova.habitat.model.db.entity.Habit

object HabitDiffCallback: DiffUtil.ItemCallback<Habit>() {

    override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}