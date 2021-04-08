package by.aermakova.habitat.view.base

import androidx.lifecycle.ViewModel
import by.aermakova.habitat.R
import by.aermakova.habitat.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val showErrorMessageCommand: SingleLiveEvent<Int> = SingleLiveEvent()

    protected fun showEmptyFieldsError(){
        showError(R.string.error_empty_fields)
    }

    protected fun showError(message: Int = R.string.error_db){
        showErrorMessageCommand.value = message
    }

    protected val _disposable = CompositeDisposable()
    val disposable: CompositeDisposable
        get() = _disposable

    override fun onCleared() {
        _disposable.clear()
        super.onCleared()
    }
}