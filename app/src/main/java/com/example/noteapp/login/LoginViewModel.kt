package com.example.noteapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel(){

    fun loginUser(email: String, password: String){

        viewModelScope.launch {

            loginRepository.loginUser(email, password)
        }

    }

}