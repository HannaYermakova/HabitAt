package by.aermakova.habitat.view.custom.dataadapter

import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.view.custom.CategoryPillButtonView
import by.aermakova.habitat.view.custom.CategoryPillView
import by.aermakova.habitat.view.observer.CategoryObservable
import by.aermakova.habitat.view.observer.CategoryObserver
import java.util.*


class CategoryPillsAdapter(private val listener: View.OnClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), CategoryObservable {

    private var categories: List<Category?>? = null
    private var categoryObservers: MutableList<CategoryObserver>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType) {
            0 -> CategoryPillCreateFirstViewHolder(CategoryPillButtonView(parent.context))
            else -> CategoryPillViewHolder(CategoryPillView(parent.context))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (categories == null || categories!!.isEmpty()) {
            0
        } else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            (holder as CategoryPillCreateFirstViewHolder).view.setOnClickListener(listener)
        } else {
            val view = (holder as CategoryPillViewHolder).view
            view.category = categories!![position]
            if (categories!!.size == 1) {
                view.isSelected = true
                view.isEnabled = false
                notifyObserverCategory(view.category!!.id)
            } else {
                if (categoryPillViews == null) {
                    categoryPillViews = HashSet()
                }
                view.setOnCheckListener { buttonView: CompoundButton?, isChecked: Boolean ->
                    if (isChecked) {
                        notifyObserverCategory(view.category!!.id)
                        for (pillView in categoryPillViews!!) {
                            if (pillView != view) {
                                pillView.isSelected = false
                            }
                        }
                    }
                }
                categoryPillViews!!.add(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (categories != null && categories!!.isNotEmpty()) categories!!.size else 1
    }

    fun setCategories(categories: List<Category?>?) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun registerObserver(o: CategoryObserver) {
        if (categoryObservers == null) {
            categoryObservers = ArrayList()
        }
        categoryObservers!!.add(o)
    }

    override fun unregisterObserver(o: CategoryObserver) {
        if (categoryObservers != null) {
            categoryObservers!!.remove(o)
            if (categoryObservers!!.isEmpty()) {
                categoryObservers = null
            }
        }
    }

    override fun notifyObserverCategory(categoryId: Long) {
        if (categoryObservers != null) {
            for (categoryObserver in categoryObservers!!) {
                categoryObserver.updateCategory(categoryId)
            }
        }
    }

    internal class CategoryPillViewHolder(val view: CategoryPillView) : RecyclerView.ViewHolder(view)

    internal class CategoryPillCreateFirstViewHolder(val view: CategoryPillButtonView) : RecyclerView.ViewHolder(view)

    companion object {
        private var categoryPillViews: MutableSet<CategoryPillView>? = null
    }

}