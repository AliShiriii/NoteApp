package com.example.noteapp.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val repository: SignUpRepository) : ViewModel() {

    fun signUpUser(user: User) = viewModelScope.launch {
        repository.signUpUser(user)
    }
}