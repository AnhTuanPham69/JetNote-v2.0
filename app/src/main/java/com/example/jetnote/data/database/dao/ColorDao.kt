package com.example.jetnote.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetnote.data.database.model.ColorDbModel

/**
 * Dao for managing Color table in the database.
 */
@Dao
interface ColorDao {

  @Query("SELECT * FROM ColorDbModel")
  fun getAll(): LiveData<List<ColorDbModel>>

  @Query("SELECT * FROM ColorDbModel")
  fun getAllSync(): List<ColorDbModel>


  @Query("SELECT * FROM ColorDbModel WHERE id LIKE :id")
  fun findById(id: Long): LiveData<ColorDbModel>

  @Query("SELECT * FROM ColorDbModel WHERE id LIKE :id")
  fun findByIdSync(id: Long): ColorDbModel

  @Insert
  fun insertAll(vararg colorDbModels: ColorDbModel)
}
