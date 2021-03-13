package com.example.noteappfinish.di

import android.content.Context
import androidx.room.Room
import com.example.noteappfinish.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context : Context) = Room.inMemoryDatabaseBuilder(
        context,NoteDatabase::class.java)
        .allowMainThreadQueries()
        .build()

}