package com.example.repository.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
   public val id: Int = 0,
    val notTitle: String,
    val noteBode: String,
    val date: String

) : Parcelable
