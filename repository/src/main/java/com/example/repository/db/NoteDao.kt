package com.example.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.repository.model.Note
import com.example.repository.model.User

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun signUpUser(loginUser: User)

    @Query("SELECT * FROM user_model_table WHERE email= :email AND password= :password")
    suspend fun loginUser(email: String?, password: String?): User?

}