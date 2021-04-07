package by.aermakova.habitat.view.custom.dataadapter

import androidx.recyclerview.widget.DiffUtil
import by.aermakova.habitat.model.utilenums.WeekdayWrapper

object WeekdayDiffCallback: DiffUtil.ItemCallback<WeekdayWrapper>() {

    override fun areItemsTheSame(oldItem: WeekdayWrapper, newItem: WeekdayWrapper): Boolean {
        return oldItem.weekday == newItem.weekday
    }

    override fun areContentsTheSame(oldItem: WeekdayWrapper, newItem: WeekdayWrapper): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}