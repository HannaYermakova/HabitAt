package by.aermakova.habitat.model.db.pref

import android.content.Context

class PreferencesManager(private val context: Context) {

    fun saveUserName(userName: String){
        Preferences.setUserName(context, userName)
    }
}