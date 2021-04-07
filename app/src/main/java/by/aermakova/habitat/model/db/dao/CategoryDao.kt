package by.aermakova.habitat.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.aermakova.habitat.model.db.entity.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAllCategory(): LiveData<List<Category>>

    @Query("SELECT * FROM category")
    suspend fun getAllCategorySuspend(): List<Category>

    @Query("SELECT * FROM category WHERE id = :id")
    fun getById(id: Long): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category?)

    @Update
    fun update(category: Category?)

    @Delete
    fun delete(category: Category?)
}