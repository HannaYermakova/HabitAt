package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.custom.HabitCardCategoryItemView

class HabitListAdapter : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
    private var habits: List<Habit?>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder(HabitCardCategoryItemView(parent.context))
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.view.setHabitItem(habits!![position])
    }

    override fun getItemCount(): Int {
        return if (habits != null) habits!!.size else 0
    }

    fun setHabits(habits: List<Habit?>?) {
        this.habits = habits
        notifyDataSetChanged()
    }

    class HabitViewHolder(val view: HabitCardCategoryItemView) : RecyclerView.ViewHolder(view)
}