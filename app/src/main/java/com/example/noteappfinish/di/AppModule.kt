package com.example.noteappfinish.di

import android.content.Context
import androidx.room.Room
import com.example.noteappfinish.database.NoteDao
import com.example.noteappfinish.database.NoteDatabase
import com.example.noteappfinish.repository.NoteRepository
import com.example.noteappfinish.repository.NoteRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context : Context)  = Room.databaseBuilder(
            context,NoteDatabase::class.java,"NoteDatabase"
    ).build()

    @Singleton
    @Provides
    fun injectDao(db : NoteDatabase) = db.dao()

    @Singleton
    @Provides
    fun injectRepository(dao : NoteDao) = NoteRepository(dao) as NoteRepositoryInterface

}