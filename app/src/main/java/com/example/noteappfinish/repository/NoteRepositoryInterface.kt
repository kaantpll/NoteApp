package com.example.noteappfinish.repository

import androidx.lifecycle.LiveData
import com.example.noteappfinish.model.Note

interface NoteRepositoryInterface {

    suspend fun insert(note : Note)
    suspend fun delete(note : Note)
    fun select() : LiveData<List<Note>>
    fun searchNote(query : String?) : LiveData<List<Note>>
    suspend fun updateNote(note : Note)

}