package by.aermakova.habitat.model.model

import com.google.gson.Gson

data class TimeModel(
    val hours: Int,
    val minutes: Int
) {
    fun toStringRepresentation(): String {
        var time = "$hours:$minutes"
        if (minutes == 0) time += "0"
        return time
    }

    fun toStringModel(): String = Gson().toJson(this)
}

fun String.toTimeModel(): TimeModel = Gson().fromJson<TimeModel>(this, TimeModel::class.java)