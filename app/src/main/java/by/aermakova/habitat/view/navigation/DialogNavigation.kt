package by.aermakova.habitat.view.navigation

import androidx.lifecycle.LiveData

interface DialogNavigation<Type> {

    fun openItemDialog(title: String ="")

    fun getDialogResult(): LiveData<Type>?

    fun setDialogResult(result:Type)
}