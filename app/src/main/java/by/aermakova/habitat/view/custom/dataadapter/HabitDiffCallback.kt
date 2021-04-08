package by.aermakova.habitat.view.custom.dataadapter

import androidx.recyclerview.widget.DiffUtil
import by.aermakova.habitat.model.model.HabitModel

object HabitDiffCallback: DiffUtil.ItemCallback<HabitModel>() {

    override fun areItemsTheSame(oldItem: HabitModel, newItem: HabitModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HabitModel, newItem: HabitModel): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}