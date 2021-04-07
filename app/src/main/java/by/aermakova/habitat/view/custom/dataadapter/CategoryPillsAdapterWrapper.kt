package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.model.CategoryWrapper
import by.aermakova.habitat.view.custom.CategoryPillButtonView
import by.aermakova.habitat.view.custom.CategoryPillView

private const val ADDITIONAL_TYPE = 0
private const val REGULAR_TYPE = 1

class CategoryPillsAdapterWrapper(
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
        return when (viewType) {
            ADDITIONAL_TYPE -> CategoryPillCreateFirstViewHolder(CategoryPillButtonView(parent.context))
            else -> CategoryPillViewHolder(CategoryPillView(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ADDITIONAL_TYPE -> (holder as CategoryPillCreateFirstViewHolder).view.setOnClickListener { openCreateCategory.invoke() }
            else -> {
                val wrapper = getItem(position)
                val view = (holder as CategoryPillViewHolder).view
                view.category = wrapper.category
                view.setOnCheckListener { _, isChecked ->
                    wrapper.isSelected = isChecked
                    update.invoke(wrapper)
                }
            }
        }
    }

    class CategoryPillViewHolder(val view: CategoryPillView) : RecyclerView.ViewHolder(view)

    class CategoryPillCreateFirstViewHolder(val view: CategoryPillButtonView) :
        RecyclerView.ViewHolder(view)
}