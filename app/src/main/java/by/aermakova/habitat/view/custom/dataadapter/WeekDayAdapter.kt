package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.utilenums.WeekDay
import by.aermakova.habitat.view.custom.WeekDayCustomToggleButton
import by.aermakova.habitat.view.custom.dataadapter.WeekDayAdapter.WeekDayToggleHolder
import by.aermakova.habitat.view.observer.WeekDayObservable
import by.aermakova.habitat.view.observer.WeekDayObserver
import java.util.*

class WeekDayAdapter : RecyclerView.Adapter<WeekDayToggleHolder>(), WeekDayObservable {
    private val weekDays: Array<WeekDay> = WeekDay.week
    private var selectedDays = BooleanArray(7)
    private var weekDayObservers: MutableList<WeekDayObserver>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDayToggleHolder {
        return WeekDayToggleHolder(WeekDayCustomToggleButton(parent.context))
    }

    override fun onBindViewHolder(holder: WeekDayToggleHolder, position: Int) {
        val view = holder.view
        view.setWeekDay(weekDays[position])
        view.isSelected = selectedDays[position]
        view.setOnCheckListener { _: CompoundButton?, isChecked: Boolean ->
            selectedDays[position] = isChecked
            notifyObserverWeekDays(selectedDays)
        }
    }

    fun setSelectedDays(selectedDays: BooleanArray) {
        this.selectedDays = selectedDays
        notifyObserverWeekDays(selectedDays)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return weekDays.size
    }

    override fun registerObserver(o: WeekDayObserver) {
        if (weekDayObservers == null) {
            weekDayObservers = ArrayList()
        }
        weekDayObservers!!.add(o)
    }

    override fun unregisterObserver(o: WeekDayObserver) {
        if (weekDayObservers != null) {
            weekDayObservers!!.remove(o)
            if (weekDayObservers!!.isEmpty()) {
                weekDayObservers = null
            }
        }
    }

    override fun notifyObserverWeekDays(days: BooleanArray?) {
        if (weekDayObservers != null) {
            for (observer in weekDayObservers!!) {
                observer.updateWeekDays(days)
            }
        }
    }

    class WeekDayToggleHolder(val view: WeekDayCustomToggleButton) : RecyclerView.ViewHolder(view)
}