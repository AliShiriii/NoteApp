package com.example.noteapp.login

import com.example.repository.db.NoteDao
import javax.inject.Inject

class LoginRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun loginUser(email: String?, password: String?) = noteDao.loginUser(email, password)

}