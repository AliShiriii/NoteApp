package com.example.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_model_table")
data class User(

    val email: String,
    val password: String,
    val confirmPassword: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0

}