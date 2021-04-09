package by.aermakova.habitat.view.custom.dataadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.CustomToggleCardColorDataBinding
import by.aermakova.habitat.model.model.CardColorWrapper


class CardColorAdapter(private val update: (CardColorWrapper) -> Unit) :
    ListAdapter<CardColorWrapper, CardColorAdapter.CardColorToggleHolder>(CardColorDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardColorToggleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CustomToggleCardColorDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.custom_toggle_card_color_data,
            parent,
            false
        )
        return CardColorToggleHolder(binding)
    }

    override fun onBindViewHolder(holder: CardColorToggleHolder, position: Int) {
        val wrapper = getItem(position)
        holder.binding.cardColor = wrapper
        (holder.binding.root as ToggleButton).setOnClickListener {
            val isChecked = (it as ToggleButton).isChecked
            if(isChecked) update.invoke(wrapper)
            notifyDataSetChanged()
        }
    }

    class CardColorToggleHolder(val binding: CustomToggleCardColorDataBinding) : RecyclerView.ViewHolder(binding.root)
}