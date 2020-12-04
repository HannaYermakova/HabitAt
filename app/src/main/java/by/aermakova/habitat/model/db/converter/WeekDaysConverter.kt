package by.aermakova.habitat.model.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object dxxWeekDaysConverter {
    private val gson = Gson()

    @kotlin.jvm.JvmStatic
    @TypeConverter
    fun stringToArray(data: String?): BooleanArray? {
        if (data == null) return null
        val arrayType = object : TypeToken<BooleanArray?>() {}.type
        return gson.fromJson(data, arrayType)
    }

    @kotlin.jvm.JvmStatic
    @TypeConverter
    fun arrayToString(days: BooleanArray?): String {
        return gson.toJson(days)
    }
}