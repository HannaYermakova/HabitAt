package by.aermakova.habitat.view.main.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarViewModel : ViewModel() {
    private val mText: MutableLiveData<String>
    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is home fragment"
    }
}