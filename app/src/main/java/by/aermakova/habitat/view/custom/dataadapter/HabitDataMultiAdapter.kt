package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.util.FunctionNoArgs
import by.aermakova.habitat.view.custom.HabitCardAdditionalView
import by.aermakova.habitat.view.custom.HabitCardView

class HabitDataMultiAdapter(private val clickListener: FunctionNoArgs) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var habitList: List<Habit?>? = null

    override fun getItemViewType(position: Int): Int {
        return if (habitList == null || position == habitList!!.size) 0 else 1
    }

    override fun getItemCount(): Int {
        return if (habitList != null) habitList!!.size + 1 else 1
    }

    fun setHabitList(habitList: List<Habit?>?) {
        this.habitList = habitList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            HabitViewAdditionHolder(HabitCardAdditionalView(parent.context))
        } else HabitViewHolder(HabitCardView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            (holder as HabitViewAdditionHolder).view.setTextVisible(!(habitList != null && habitList!!.isNotEmpty()))
        } else (holder as HabitViewHolder).view.setHabitItem(habitList!![position])
    }

    internal class HabitViewHolder(val view: HabitCardView) : RecyclerView.ViewHolder(view)

    internal inner class HabitViewAdditionHolder(val view: HabitCardAdditionalView) :
        RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener { clickListener.invoke() }
        }
    }

}