package com.example.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repository.model.Note

@Database(entities = [Note::class], version = 5, exportSchema = false)
abstract class NoteDataBase: RoomDatabase() {

    //fun getDao from dao
    abstract fun getNote(): NoteDao

}