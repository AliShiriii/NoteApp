package com.example.noteapp.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatBase(@ApplicationContext context: Context): NoteDatabase {

        return Room.databaseBuilder(context, NoteDatabase::class.java, "db_note")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: NoteDatabase): NoteDao {

        return dataBase.getNote()

    }

}