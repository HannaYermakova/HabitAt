package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.custom.HabitCardCategoryItemView


class HabitListAdapter : ListAdapter<Habit, HabitListAdapter.HabitViewHolder>(HabitDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder(HabitCardCategoryItemView(parent.context))
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.view.setHabitItem(getItem(position))
    }

    class HabitViewHolder(val view: HabitCardCategoryItemView) : RecyclerView.ViewHolder(view)
}