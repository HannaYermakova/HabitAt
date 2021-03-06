package by.aermakova.habitat.model

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class Preferences private constructor() {
    private fun getPreferencesEditor(context: Context): Editor {
        return getPreferences(context).edit()
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SETTING_PREFS, Context.MODE_PRIVATE)
    }

    fun getUserName(context: Context): String? {
        return getPreferences(context).getString(USER_NAME, null)
    }

    fun setUserName(context: Context, name: String?) {
        getPreferencesEditor(context).putString(USER_NAME, name).apply()
    }

    companion object {
        var instance: Preferences? = null
            get() {
                if (field == null) {
                    synchronized(Preferences::class.java) {
                        if (field == null) {
                            field = Preferences()
                        }
                    }
                }
                return field
            }
            private set
        private const val SETTING_PREFS = "settings_prefs"
        private const val USER_NAME = "user_name"
    }
}