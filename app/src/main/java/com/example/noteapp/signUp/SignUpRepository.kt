package com.example.noteapp.signUp

import com.example.repository.db.NoteDao
import com.example.repository.model.User
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun signUpUser(user: User) = noteDao.signUpUser(user)
}