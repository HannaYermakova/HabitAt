package by.aermakova.habitat.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.model.TimeModel
import by.aermakova.habitat.model.useCase.SelectWeekdaysUseCase
import by.aermakova.habitat.view.custom.layoutmanager.ItemOffsetDecoration
import by.aermakova.habitat.view.custom.layoutmanager.SpanningLinearLayoutManager
import by.aermakova.habitat.view.custom.weekdaysStrategy.WeekdaysStrategy
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams
import java.util.*

typealias FunctionNoArgs = () -> Unit
typealias FunctionString = (String) -> Unit
typealias FunctionLong = (Long) -> Unit

@BindingAdapter("app:onClick")
fun clickListener(view: View, listener: FunctionNoArgs?) {
    view.setOnClickListener {
        listener?.invoke()
    }
}

@BindingAdapter("app:visibility")
fun setVisibility(
    view: View,
    isVisible: Boolean?
) {
    view.visibility = isVisible?.let { if (isVisible) View.VISIBLE else View.GONE } ?: View.GONE
}

@BindingAdapter("app:onStopTracking")
fun setSeekBarSettings(
    seekBarDuration: IndicatorSeekBar,
    durationInDays: MutableLiveData<Int>?
) {
    seekBarDuration.onSeekChangeListener = object : OnSeekChangeListener {
        override fun onSeeking(seekParams: SeekParams) {}
        override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {}
        override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {
            durationInDays?.value = seekBar.progress
        }
    }
}

@BindingAdapter("app:selectedTime")
fun setSelectedTime(
    textView: TextView,
    selectedTime: TimeModel?
) {
    textView.text = selectedTime?.toStringRepresentation() ?: "10:00"
}

@BindingAdapter("app:addWeekdayStrategy")
fun addWeekdayStrategy(
    spinner: Spinner,
    selectStrategyListener: ((Int) -> Unit)?
) {
    val variants: MutableList<String> = ArrayList()
    val resources = spinner.context.resources
    for (weekdaysStrategy in WeekdaysStrategy.values()) {
        variants.add(weekdaysStrategy.position, resources.getString(weekdaysStrategy.titleId))
    }
    val spinnerAdapter =
        ArrayAdapter(spinner.context, R.layout.schedule_spinner_row, R.id.variant, variants)
    spinnerAdapter.setDropDownViewResource(R.layout.schedule_spinner_item)
    with(spinner) {
        adapter = spinnerAdapter
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectStrategyListener?.invoke(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}

@BindingAdapter("app:toggleIsChecked")
fun setToggleButtonListener(
    toggleButton: ToggleButton,
    toggleButtonListener: MutableLiveData<Boolean>?
) {
    toggleButton.setOnCheckedChangeListener { _, checked ->
        toggleButtonListener?.value = checked
    }
}

@BindingAdapter("app:tempTitle")
fun editTitleListener(
    editText: EditText,
    tempTitle: MutableLiveData<String>?
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            tempTitle?.value = editText.text.toString()
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
    "app:bindWeekdaysSelector"
)
fun bindWeekdaysSelector(
    recyclerView: RecyclerView,
    selectWeekdays: SelectWeekdaysUseCase?
) {
    selectWeekdays?.let {
        with(recyclerView) {
            layoutManager = SpanningLinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = it.weekdayAdapter
            it.setInitialWeek()
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