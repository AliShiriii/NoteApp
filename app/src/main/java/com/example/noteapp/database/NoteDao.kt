package com.example.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Note

@Dao
interface NoteDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    //Update
    @Update()
    suspend fun updateNote(note: Note)

    //Delete
    @Delete()
    suspend fun deleteNote(note: Note)

    //Query for get all notes
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAlNotes(): LiveData<List<Note>>

    //Query fore search notes
    @Query("SELECT * FROM notes ORDER BY notTitle LIKE :query OR noteBode LIKE :query")
    fun searchNotes(query: String?): LiveData<List<Note>>
}