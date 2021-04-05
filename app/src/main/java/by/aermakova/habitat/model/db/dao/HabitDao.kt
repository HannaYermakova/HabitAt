package by.aermakova.habitat.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.aermakova.habitat.model.db.entity.Habit

@Dao
interface HabitDao {

    @Query("SELECT * FROM habit")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("SELECT * FROM habit WHERE categoryId = :categoryId ")
    fun getHabitsByCategoryId(categoryId: Long): LiveData<List<Habit?>?>?

    @Query("SELECT * FROM habit WHERE id = :id")
    fun getById(id: Long): Habit?

    @Query("SELECT * FROM habit WHERE startTime = :startTime")
    fun getByStartTime(startTime: Long): Habit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(habit: Habit?)

    @Update
    fun update(habit: Habit?)

    @Delete
    fun delete(habit: Habit?)
}