package com.example.noteapp.home

import com.example.noteapp.database.NoteDao
import com.example.noteapp.model.Note
import javax.inject.Inject

class HomeRepository @Inject constructor(private val dao: NoteDao) {

    suspend fun insertNote(note: Note) = dao.insertNote(note)
    suspend fun updateNote(note: Note) = dao.updateNote(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
    fun getAllNotes() = dao.getAlNotes()
    fun searchNotes(note: String?) = dao.searchNotes(note)


}