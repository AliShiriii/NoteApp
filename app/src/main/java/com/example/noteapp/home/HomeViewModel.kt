package com.example.noteapp.home

import androidx.lifecycle.ViewModel
import com.example.noteapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {


    suspend fun insertNote(note: Note) = repository.insertNote(note)
    suspend fun updateNote(note: Note) = repository.updateNote(note)
    suspend fun deleteNote(note: Note) = repository.deleteNote(note)
    fun getAllNotes() = repository.getAllNotes()
    fun searchNotes(note: String?) = repository.searchNotes(note)

}