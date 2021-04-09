package by.aermakova.habitat.model.useCase

import android.text.TextUtils
import android.util.Log
import by.aermakova.habitat.model.db.pref.PreferencesManager
import by.aermakova.habitat.util.FunctionNoArgs
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


class SaveUserNameUseCase(
    private val preferencesManager: PreferencesManager
) {

    fun saveValidUserName(
        enteredUserName: String,
        disposable: CompositeDisposable,
        successAction: FunctionNoArgs
    ) {
        val userName = enteredUserName.trim { it <= ' ' }
        if (isUserNameValid(userName))
            saveUserNameLocal(userName, disposable, successAction)
        else showErrorMessage()
    }

    private fun saveUserNameLocal(
        userName: String,
        disposable: CompositeDisposable,
        successAction: FunctionNoArgs
    ) {
        disposable.add(
            Single.create<Unit> {
                it.onSuccess(preferencesManager.saveUserName(userName))
            }.subscribe(
                { successAction.invoke() },
                {
                    it.printStackTrace()
                    showErrorMessage()
                }
            )
        )
    }

    private fun showErrorMessage() {
        Log.d("A_SaveUserNameUseCase", "showErrorMessage")
    }

    private fun isUserNameValid(userName: String): Boolean {
        return !TextUtils.isEmpty(userName)
    }
}