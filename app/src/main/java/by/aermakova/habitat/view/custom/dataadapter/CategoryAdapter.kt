package by.aermakova.habitat.view.custom.dataadapter

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.view.custom.CategoryHabitCardView
import by.aermakova.habitat.view.custom.dataadapter.CategoryAdapter.CategoryViewHolder

class CategoryAdapter(private val activity: Activity) : RecyclerView.Adapter<CategoryViewHolder>() {
    private var categories: List<Category?>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryHabitCardView(parent.context))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val view = holder.view
        view.setCategory(categories!![position])
        val args = Bundle()
        args.putParcelable(BUNDLE_TAG, categories!![position])
        view.setOnClickListener {
            Navigation.findNavController(activity,
                    activity.findViewById<View>(R.id.app_host_fragment).id)
                    .navigate(R.id.action_mainFlowFragment_to_categoryItemFragment, args)
        }
    }

    override fun getItemCount(): Int {
        return if (categories != null) categories!!.size else 0
    }

    fun setCategories(categories: List<Category?>?) {
        this.categories = categories
        notifyDataSetChanged()
    }

    class CategoryViewHolder internal constructor(val view: CategoryHabitCardView) : RecyclerView.ViewHolder(view)

    companion object {
        private const val BUNDLE_TAG = "category"
    }

}