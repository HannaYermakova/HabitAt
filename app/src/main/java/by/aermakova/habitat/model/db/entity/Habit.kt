package by.aermakova.habitat.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import by.aermakova.habitat.model.db.converter.WeekDaysConverter
import java.util.*

@Entity
class Habit {
    @kotlin.jvm.JvmField
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var title: String? = null
    var day = 0
    @kotlin.jvm.JvmField
    var startTime: Long = 0
    var categoryId: Long = 0

    @TypeConverters(WeekDaysConverter::class)
    lateinit var weekDays: BooleanArray
    var isNotificationEnable = false
    var hours = 0
    var minute = 0
    var markedDays = 0

    constructor() {}
    constructor(title: String?, day: Int, startTime: Long,
                categoryId: Long, weekDays: BooleanArray, notificationEnable: Boolean, selectedDays: Int) {
        this.title = title
        this.day = day
        this.startTime = startTime
        this.categoryId = categoryId
        this.weekDays = weekDays
        isNotificationEnable = notificationEnable
        markedDays = selectedDays
    }

    fun setNotificationTime(hours: Int, minutes: Int) {
        this.hours = hours
        minute = minutes
    }

    fun updateMarkedDays(i: Int) {
        markedDays = markedDays + i
    }

    override fun toString(): String {
        return "Habit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", day=" + day +
                ", startTime=" + startTime +
                ", categoryId=" + categoryId +
                ", weekDays=" + Arrays.toString(weekDays) +
                ", notificationEnable=" + isNotificationEnable +
                ", hours=" + hours +
                ", minute=" + minute +
                ", markedDays=" + markedDays +
                '}'
    }
}