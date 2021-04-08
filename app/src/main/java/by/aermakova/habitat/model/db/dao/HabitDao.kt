package by.aermakova.habitat.model.db.dao

import androidx.room.*
import by.aermakova.habitat.model.db.entity.Habit

@Dao
interface HabitDao {

    @Query("SELECT * FROM habit")
    suspend fun getAllHabits(): List<Habit>

    @Query("SELECT * FROM habit WHERE categoryId = :categoryId ")
    suspend fun getHabitsByCategoryId(categoryId: Long): List<Habit>

    @Query("SELECT * FROM habit WHERE id = :id")
    fun getById(id: Long): Habit?

    @Query("SELECT * FROM habit WHERE startTime = :startTime")
    fun getByStartTime(startTime: Long): Habit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit)

    @Update
    fun update(habit: Habit?)

    @Delete
    fun delete(habit: Habit?)
}