package by.aermakova.habitat.model.model.habit

import by.aermakova.habitat.model.db.entity.Habit

data class HabitModel(
    val id: Long = 0,
    val title: String,
    val day: Int,
    val startTime: Long,
    val categoryId: Long,
    val weekDays: BooleanArray,
    val isNotificationEnable: Boolean,
    val hours: Int,
    val minute: Int,
    val markedDays: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HabitModel

        if (id != other.id) return false
        if (title != other.title) return false
        if (day != other.day) return false
        if (startTime != other.startTime) return false
        if (categoryId != other.categoryId) return false
        if (!weekDays.contentEquals(other.weekDays)) return false
        if (isNotificationEnable != other.isNotificationEnable) return false
        if (hours != other.hours) return false
        if (minute != other.minute) return false
        if (markedDays != other.markedDays) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + day
        result = 31 * result + startTime.hashCode()
        result = 31 * result + categoryId.hashCode()
        result = 31 * result + weekDays.contentHashCode()
        result = 31 * result + isNotificationEnable.hashCode()
        result = 31 * result + hours
        result = 31 * result + minute
        result = 31 * result + markedDays
        return result
    }
}

fun Habit.toModel(): HabitModel {
    return HabitModel(
        id,
        title ?: "",
        day,
        startTime,
        categoryId,
        weekDays,
        isNotificationEnable,
        hours,
        minute,
        markedDays
    )
}

fun HabitModel.toEntity(): Habit {
    return Habit(
        title = title,
        day = day,
        categoryId = categoryId,
        startTime = startTime,
        weekDays = weekDays,
        isNotificationEnable = isNotificationEnable,
        hours = hours,
        minute = minute,
        markedDays = markedDays
    )
}