package by.aermakova.habitat.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.model.model.*
import by.aermakova.habitat.model.useCase.SelectWeekdaysUseCase
import by.aermakova.habitat.model.useCase.calendar.ShowWeekCalendarUseCase
import by.aermakova.habitat.model.useCase.weekdaysStrategy.WeekdaysStrategy
import by.aermakova.habitat.model.utilenums.CardColor
import by.aermakova.habitat.view.custom.dataadapter.calendar.CalendarDayAdapter
import by.aermakova.habitat.view.custom.layoutmanager.ItemOffsetDecoration
import by.aermakova.habitat.view.custom.layoutmanager.SpanningLinearLayoutManager
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

@BindingAdapter(
    "app:text"
)
fun addText(
    view: TextView,
    textResource: Int?
) {
    view.text = textResource?.let { view.resources.getString(it) } ?: ""
}

@BindingAdapter(
    "app:dayNumber"
)
fun addDayNumber(
    view: TextView,
    dayNumber: Int?
) {
    view.text = dayNumber?.toString() ?: ""
}

@BindingAdapter("app:visibility")
fun setVisibility(
    view: View,
    isVisible: Boolean?
) {
    view.visibility = isVisible?.let { if (isVisible) View.VISIBLE else View.GONE } ?: View.GONE
}

@BindingAdapter("app:invisibility")
fun setInvisibility(
    view: View,
    isVisible: Boolean?
) {
    view.visibility =
        isVisible?.let { if (isVisible) View.VISIBLE else View.INVISIBLE } ?: View.INVISIBLE
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

@BindingAdapter(
    "app:setToggleText"
)
fun setToggleText(
    toggleButton: ToggleButton,
    textResource: Int?
) {
    textResource?.let {
        val title = toggleButton.resources.getString(it)
        toggleButton.apply {
            text = title
            textOff = title
            textOn = title
        }
    }
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
    habitMultiAdapter: ListAdapter<HabitModel, out RecyclerView.ViewHolder>?
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
    habitAdapter: ListAdapter<HabitModel, out RecyclerView.ViewHolder>?
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
            layoutManager =
                SpanningLinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
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
    categoryAdapter: ListAdapter<CategoryModel, out RecyclerView.ViewHolder>?
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
    "app:bindCategorySelector"
)
fun bindCategorySelector(
    recyclerView: RecyclerView,
    categoryAdapter: ListAdapter<CategoryWrapper, out RecyclerView.ViewHolder>?
) {
    categoryAdapter?.let {
        with(recyclerView) {
            layoutManager =
                LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }
}

@BindingAdapter(
    "app:bindColorsSelector"
)
fun bindColorsSelector(
    recyclerView: RecyclerView,
    colorAdapter: ListAdapter<CardColorWrapper, out RecyclerView.ViewHolder>?
) {
    colorAdapter?.let {
        with(recyclerView) {
            layoutManager = SpanningLinearLayoutManager(
                recyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = colorAdapter
        }
    }
}

@BindingAdapter(
    "app:bindCalendarAdapter"
)
fun bindColorsSelector(
    recyclerView: RecyclerView,
    showWeekCalendarUseCase: ShowWeekCalendarUseCase?
) {
    showWeekCalendarUseCase?.let {
        with(recyclerView) {
            layoutManager = SpanningLinearLayoutManager(
                recyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = it.adapter
        }
    }
}

@BindingAdapter(
    "app:addWeek"
)
fun addWeek(
    recyclerView: RecyclerView,
    list: Array<CalendarDay>?
) {
    list?.let {
        (recyclerView.adapter as CalendarDayAdapter).submitList(it.toList())
    }
}

@BindingAdapter(
    "app:categoryTitleColor"
)
fun setCategoryTitleColor(view: TextView, category: CategoryModel?) {
    category?.let {
        val color = CardColor.getColorById(it.color)
        view.setTextColor(ContextCompat.getColor(view.context, color.textColorId))
    }
}

@BindingAdapter(
    "app:categoryBackgroundColor"
)
fun setCategoryBackgroundColor(view: View, category: CategoryModel?) {
    category?.let {
        val color = CardColor.getColorById(it.color)
        view.setBackgroundColor(ContextCompat.getColor(view.context, color.cardColorId))
    }
}

@BindingAdapter(
    "app:bindCategoryWrapper",
    "app:isRectangle"
)
fun bindCategoryWrapper(
    categoryToggle: ToggleButton,
    categoryColorId: Int?,
    isRectangle: Boolean?
) {
    categoryColorId?.let {
        val context = categoryToggle.context
        val color = CardColor.getColorById(it)
        categoryToggle.background = generateSelector(color, context, isRectangle)
        categoryToggle.setTextColor(ContextCompat.getColor(context, color.textColorId))
    }
}

@BindingAdapter(
    "app:isCategoryChecked"
)
fun bindCategoryCheckedListener(
    categoryToggle: ToggleButton,
    isChecked: Boolean?
) {
    isChecked?.let {
        categoryToggle.isChecked = it
        Log.d("A_BindingAdapter", "${categoryToggle.isChecked}")
    }
}

private fun generateSelector(color: CardColor, context: Context, isRectangle: Boolean?): Drawable {
    val drawable = StateListDrawable()
    drawable.addState(
        intArrayOf(android.R.attr.state_checked),
        generateDrawable(color.cardColorId, color.textColorId, context, isRectangle)
    )
    drawable.addState(
        intArrayOf(android.R.attr.state_enabled),
        generateDrawable(color.cardColorId, 0, context, isRectangle)
    )
    return drawable
}

private fun generateDrawable(
    backgroundColor: Int,
    borderColor: Int,
    context: Context,
    isRectangle: Boolean?
): Drawable {
    val shape = GradientDrawable()
    shape.shape = isRectangle?.let { if (it) GradientDrawable.RECTANGLE else GradientDrawable.OVAL }
        ?: GradientDrawable.RECTANGLE
    shape.cornerRadius = UiUtils.CORNER_RADIUS.toFloat()
    shape.setColor(ContextCompat.getColor(context, backgroundColor))
    if (borderColor > 0) shape.setStroke(
        UiUtils.STROKE_WIDTH,
        ContextCompat.getColor(context, borderColor)
    ) else shape.setStroke(UiUtils.STROKE_WIDTH, Color.TRANSPARENT)
    return shape
}