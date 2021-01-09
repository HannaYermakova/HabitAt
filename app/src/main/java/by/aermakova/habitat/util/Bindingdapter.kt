package by.aermakova.habitat.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.custom.dataadapter.HabitDataMultiAdapter
import by.aermakova.habitat.view.custom.layoutmanager.ItemOffsetDecoration
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable

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
    "app:bindListHabits",
    "app:addDisposable",
    "app:navigateFunction"
)
fun bindCommonListToRecycler(
    recyclerView: RecyclerView,
    items: Observable<List<Habit>>?,
    disposable: CompositeDisposable?,
    navigateFunction: FunctionNoArgs?
) {

    if (items != null && disposable != null && navigateFunction != null) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.addItemDecoration(ItemOffsetDecoration(10))
        val mHabitDataAdapter = HabitDataMultiAdapter(navigateFunction)
        recyclerView.adapter = mHabitDataAdapter
        disposable.add(
            items.subscribe(
                { mHabitDataAdapter.setHabitList(it) },
                { it.printStackTrace() })
        )
    }
}