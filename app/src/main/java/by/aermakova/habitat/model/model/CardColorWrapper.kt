package by.aermakova.habitat.model.model

import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.utilenums.CardColor

class CardColorWrapper(val cardColor: CardColor, val isSelected: MutableLiveData<Boolean>)