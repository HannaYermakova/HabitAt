package by.aermakova.habitat.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.custom.layoutmanager.ItemOffsetDecoration
import io.reactivex.Observer

typealias FunctionNoArgs = () -> Unit
typealias FunctionString = (String) -> Unit
typealias FunctionLong = (Long) -> Unit

@BindingAdapter("app:onClick")
fun clickListener(view: View, listener: FunctionNoArgs?) {
    view.setOnClickListener {
        listener?.invoke()
    }
}

@BindingAdapter("app:tempTitle")
fun editTitleListener(
    editText: EditText,
    tempTitle: Observer<String>?
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            tempTitle?.onNext(editText.text.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

@BindingAdapter(
    "app:bindHabitsAdapter"
)
fun bindHabitsListToRecycler(
    recyclerView: RecyclerView,
    habitMultiAdapter: ListAdapter<Habit, out RecyclerView.ViewHolder>?
) {

    habitMultiAdapter?.let {
        with(recyclerView) {
            layoutManager =
                LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(ItemOffsetDecoration(10))
            adapter = habitMultiAdapter
        }
    }
}

@BindingAdapter(
    "app:bindSimpleHabitsAdapter"
)
fun bindSimpleHabitsListToRecycler(
    recyclerView: RecyclerView,
    habitAdapter: ListAdapter<Habit, out RecyclerView.ViewHolder>?
) {

    habitAdapter?.let {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(ItemOffsetDecoration(8))
            adapter = habitAdapter
        }
    }
}

@BindingAdapter(
    "app:bindCategoryAdapter"
)
fun bindCategoryListToRecycler(
    recyclerView: RecyclerView,
    categoryAdapter: ListAdapter<Category, out RecyclerView.ViewHolder>?
) {
    categoryAdapter?.let {
        with(recyclerView) {
            layoutManager =
                LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }
    }
}

@BindingAdapter(
    "app:bindColor",
    "app:isCardColor"
)
fun bindBackgroundColor(view: View, colorId: Int?, isCardColor: Boolean?) {
    if (colorId != null && isCardColor != null) {
/*        val color = CardColor.getColorById(colorId)
        view.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                if (isCardColor) {
                    color.cardColorId
                } else color.textColorId
            )
        )*/
    }
}