package by.aermakova.habitat.view.main.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatisticViewModel : ViewModel() {
    private val mText: MutableLiveData<String?>
    val text: LiveData<String?>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is notifications fragment"
    }
}