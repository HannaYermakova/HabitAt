package by.aermakova.habitat.view.custom.dataadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.CustomCategoryCardBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.view.custom.dataadapter.CategoryAdapter.CategoryViewHolder
import by.aermakova.habitat.view.navigation.MainFlowNavigation

class CategoryAdapter(private val routing: MainFlowNavigation) :
    ListAdapter<Category, CategoryViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CustomCategoryCardBinding =
            DataBindingUtil.inflate(inflater, R.layout.custom_category_card, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        category.apply {
            holder.binding.category = this
            holder.binding.root.setOnClickListener { routing.navigateToShowDetailsFragment(id) }
        }
    }

    class CategoryViewHolder(val binding: CustomCategoryCardBinding) :
        RecyclerView.ViewHolder(binding.root)

}