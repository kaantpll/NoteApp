package com.example.noteappfinish.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteappfinish.model.Note
import dagger.multibindings.IntoSet

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNote() : LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR note LIKE:query")
    fun searchNote(query : String?) : LiveData<List<Note>>

}