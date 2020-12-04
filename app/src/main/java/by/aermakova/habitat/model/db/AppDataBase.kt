package by.aermakova.habitat.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.aermakova.habitat.model.db.dao.CategoryDao
import by.aermakova.habitat.model.db.dao.HabitDao
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit

@Database(entities = [Habit::class, Category::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun categoryDao(): CategoryDao
}