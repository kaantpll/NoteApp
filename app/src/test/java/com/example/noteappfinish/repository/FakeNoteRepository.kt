package com.example.noteappfinish.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.noteappfinish.model.Note

class FakeNoteRepository : NoteRepositoryInterface {

    var notes = mutableListOf<Note>()
    var noteLiveData = MutableLiveData<List<Note>>(notes)

    override suspend fun insert(note: Note) {
        notes.add(note)
        refresh()
    }

    override suspend fun delete(note: Note) {
       notes.remove(note)
        refresh()
    }

    override fun select(): LiveData<List<Note>> {
       return noteLiveData
    }

    override fun searchNote(query: String?): LiveData<List<Note>> {
      return noteLiveData
    }

    override suspend fun updateNote(note: Note) {
        notes.clear()
        notes.add(note)
        refresh()
    }
    fun refresh(){
        noteLiveData.postValue(notes)
    }
}