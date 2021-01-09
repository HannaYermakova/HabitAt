package by.aermakova.habitat.model.db.pref

import android.content.Context
import by.aermakova.habitat.model.Preferences

class PreferencesManager(private val context: Context) {

    fun saveUserName(userName: String){
        Preferences.setUserName(context, userName)
    }
}