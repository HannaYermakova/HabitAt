package by.aermakova.habitat.model.useCase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.model.CardColorWrapper
import by.aermakova.habitat.model.utilenums.CardColor
import by.aermakova.habitat.view.custom.dataadapter.CardColorAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SelectColorUseCase {

    val colorAdapter = CardColorAdapter {
        updateColorsAfterSelect(it)
    }

    private val _allColors = MutableLiveData<List<CardColorWrapper>>()
    val allColors : LiveData<List<CardColorWrapper>>
        get() = _allColors

    private var _selectedColor: CardColorWrapper? = null
    val selectedColor: CardColorWrapper?
        get() = _selectedColor

    fun setColors(colors: List<CardColorWrapper>) {
        colorAdapter.submitList(colors)
    }

    fun loadColors(scope: CoroutineScope) {
        scope.launch {
            _allColors.value = withContext(Dispatchers.IO){
                getColorsWrappers()
            }
        }
    }

    private fun updateColorsAfterSelect(colorWrapper: CardColorWrapper) {
        this._selectedColor = colorWrapper
        _allColors.value?.let {
            it.map { wrapper ->
                Log.d("A_SelectColorUseCase", "${wrapper.cardColor == colorWrapper.cardColor}")
                wrapper.isSelected.value = wrapper.cardColor.id == colorWrapper.cardColor.id
            }
        }
    }

    private fun getColorsWrappers() =
        CardColor.values().map { CardColorWrapper(it, MutableLiveData(false)) }
}