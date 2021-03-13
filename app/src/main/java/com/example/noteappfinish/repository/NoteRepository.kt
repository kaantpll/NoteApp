package com.example.noteappfinish.repository

import androidx.lifecycle.LiveData
import com.example.noteappfinish.database.NoteDao
import com.example.noteappfinish.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
        private var dao : NoteDao
) : NoteRepositoryInterface{

    override suspend fun insert(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun delete(note: Note) {
        dao.deleteNote(note)
    }

    override fun select(): LiveData<List<Note>> {
        return dao.getAllNote()
    }

    override fun searchNote(query : String?): LiveData<List<Note>> {
       return dao.searchNote(query)
    }

    override suspend fun updateNote(note: Note) {
       return dao.updateNote(note)
    }
}