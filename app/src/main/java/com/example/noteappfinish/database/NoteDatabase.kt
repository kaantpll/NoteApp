package com.example.noteappfinish.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappfinish.model.Note

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun dao() : NoteDao
}