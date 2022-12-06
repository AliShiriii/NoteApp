package com.example.noteapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _getNotes = MutableLiveData<List<Note>>()
    val getNotes: LiveData<List<Note>> get() = _getNotes

    //insertNote
    fun insertNote(note: Note) = viewModelScope.launch { repository.insertNote(note) }

    //updateNote
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }

    //deleteNote
    fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

    //searchOneOfNote
    fun searchNotes(note: String?) = repository.searchNotes(note)

    //getAllNote
    fun getAllNotes() = viewModelScope.launch {

        val data = repository.getAllNotes()

        _getNotes.value = data.value

    }
}