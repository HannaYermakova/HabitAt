package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.custom.HabitCardAdditionalView
import by.aermakova.habitat.view.custom.HabitCardView
import by.aermakova.habitat.view.navigation.MainFlowNavigation

private const val ADDITIONAL_TYPE = 0
private const val REGULAR_TYPE = 1

class HabitDataMultiAdapter(private val routing: MainFlowNavigation) :
    ListAdapter<Habit, RecyclerView.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Habit>() {

        override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount == 1 || position == itemCount - 1) ADDITIONAL_TYPE else REGULAR_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ADDITIONAL_TYPE -> HabitViewAdditionHolder(HabitCardAdditionalView(parent.context))
            else -> HabitViewHolder(HabitCardView(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ADDITIONAL_TYPE -> {
                (holder as HabitViewAdditionHolder).view.setTextVisible(itemCount <= 1)
                holder.view.setOnClickListener { routing.navigateToAddNewElementFragment() }
            }
            else -> {
                (holder as HabitViewHolder).view.setHabitItem(getItem(position))
                holder.view.setOnClickListener {
                    routing.navigateToShowDetailsFragment(getItem(position).id)
                }
            }
        }
    }

    class HabitViewHolder(val view: HabitCardView) : RecyclerView.ViewHolder(view)

    class HabitViewAdditionHolder(val view: HabitCardAdditionalView) : RecyclerView.ViewHolder(view)
}