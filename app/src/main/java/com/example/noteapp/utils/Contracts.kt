package com.example.noteapp.utils

import android.content.Context
import android.widget.Toast

object Contracts {

    //email and password
    const val USER_ID = "email"
    const val PASSWORD_ID = "password"

    fun Context.showToast(message: String) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}