package by.aermakova.habitat.view.custom.dataadapter.color

import androidx.recyclerview.widget.DiffUtil
import by.aermakova.habitat.model.model.CardColorWrapper


object CardColorDiffCallback: DiffUtil.ItemCallback<CardColorWrapper>() {

    override fun areItemsTheSame(oldItem: CardColorWrapper, newItem: CardColorWrapper): Boolean {
        return oldItem.cardColor.id == newItem.cardColor.id
    }

    override fun areContentsTheSame(oldItem: CardColorWrapper, newItem: CardColorWrapper): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}