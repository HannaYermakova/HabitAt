package by.aermakova.habitat.view.custom.dataadapter

import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import by.aermakova.habitat.model.utilenums.CardColor
import by.aermakova.habitat.view.custom.CardColorCustomToggleButton
import by.aermakova.habitat.view.custom.dataadapter.CardColorAdapter.CardColorToggleHolder
import by.aermakova.habitat.view.observer.ColorObservable
import by.aermakova.habitat.view.observer.ColorObserver
import java.util.*

class CardColorAdapter : RecyclerView.Adapter<CardColorToggleHolder>(), ColorObservable {
    private val colors: Array<CardColor> = CardColor.cardColors
    private var observerColors: MutableList<ColorObserver>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardColorToggleHolder {
        return CardColorToggleHolder(CardColorCustomToggleButton(parent.context))
    }

    override fun onBindViewHolder(holder: CardColorToggleHolder, position: Int) {
        val view = holder.view
        view.color = colors[position]
        if (colorCustomToggleButtons == null) {
            colorCustomToggleButtons = HashSet()
        }
        view.setOnCheckListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                notifyObserver(view.color!!.id)
                for (toggleButton in colorCustomToggleButtons!!) {
                    if (toggleButton != view) {
                        toggleButton.isSelected = false
                    }
                }
            }
        }
        colorCustomToggleButtons!!.add(view)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun registerObserver(o: ColorObserver) {
        if (observerColors == null) {
            observerColors = LinkedList()
        }
        observerColors!!.add(o)
    }

    override fun unregisterObserver(o: ColorObserver) {
        if (observerColors != null && observerColors!!.isNotEmpty()) {
            observerColors!!.remove(o)
            if (observerColors!!.size == 0) observerColors = null
        }
    }

    override fun notifyObserver(color: Int) {
        if (observerColors != null) {
            for (observerColor in observerColors!!) {
                observerColor.update(color)
            }
        }
    }

    class CardColorToggleHolder(val view: CardColorCustomToggleButton) : RecyclerView.ViewHolder(view)

    companion object {
        private var colorCustomToggleButtons: MutableSet<CardColorCustomToggleButton>? = null
    }
}