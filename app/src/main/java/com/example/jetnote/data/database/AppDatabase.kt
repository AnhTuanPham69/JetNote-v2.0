package com.example.jetnote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetnote.data.database.dao.ColorDao
import com.example.jetnote.data.database.dao.NoteDao
import com.example.jetnote.data.database.model.ColorDbModel
import com.example.jetnote.data.database.model.NoteDbModel

/**
 * App's database.
 *
 * It contains two tables: Note table and Color table.
 */
@Database(entities = [NoteDbModel::class, ColorDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  companion object {
    const val DATABASE_NAME = "note-maker-database"
  }

  abstract fun noteDao(): NoteDao

  abstract fun colorDao(): ColorDao
}
