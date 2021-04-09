package by.aermakova.habitat.view.custom.dataadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.CustomPillCreateFirstHabitDataBinding
import by.aermakova.habitat.databinding.CustomToggleCategoryPillBinding
import by.aermakova.habitat.model.model.CategoryWrapper

private const val ADDITIONAL_TYPE = 0
private const val REGULAR_TYPE = 1

class CategoryPillsAdapter(
    private val update: (CategoryWrapper) -> Unit,
    private val openCreateCategory: () -> Unit
) :
    ListAdapter<CategoryWrapper, RecyclerView.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CategoryWrapper>() {

        override fun areItemsTheSame(oldItem: CategoryWrapper, newItem: CategoryWrapper): Boolean {
            return oldItem.category == newItem.category
        }

        override fun areContentsTheSame(
            oldItem: CategoryWrapper,
            newItem: CategoryWrapper
        ): Boolean {
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
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ADDITIONAL_TYPE -> {
                val binding: CustomPillCreateFirstHabitDataBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.custom_pill_create_first_habit_data,
                    parent,
                    false
                )
                CategoryPillCreateFirstViewHolder(binding)
            }
            else -> {

                val binding: CustomToggleCategoryPillBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.custom_toggle_category_pill,
                    parent,
                    false
                )
                CategoryPillViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ADDITIONAL_TYPE -> {
                (holder as CategoryPillCreateFirstViewHolder).binding.root.setOnClickListener { openCreateCategory.invoke() }
            }
            else -> {
                val wrapper = getItem(position)
                val binding = (holder as CategoryPillViewHolder).binding
                binding.category = wrapper
                (binding.root as ToggleButton).setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) update.invoke(wrapper)
                    notifyDataSetChanged()
                }
            }
        }
    }

    class CategoryPillViewHolder(val binding: CustomToggleCategoryPillBinding) :
        RecyclerView.ViewHolder(binding.root)

    class CategoryPillCreateFirstViewHolder(val binding: CustomPillCreateFirstHabitDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}