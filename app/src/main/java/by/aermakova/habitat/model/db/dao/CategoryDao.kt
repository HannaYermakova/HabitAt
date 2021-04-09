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
   suspend fun getById(id: Long): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category?):Long

    @Update
    fun update(category: Category?)

    @Delete
    fun delete(category: Category?)
}