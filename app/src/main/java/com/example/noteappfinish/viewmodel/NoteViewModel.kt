package com.example.noteappfinish.viewmodel

import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappfinish.model.Note
import com.example.noteappfinish.repository.NoteRepositoryInterface
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class NoteViewModel @ViewModelInject constructor(
        private val repository : NoteRepositoryInterface
): ViewModel() {


    val noteList = repository.select()

    fun createNote(noteTitle : String,noteBody : String){

        val note = Note(noteTitle,noteBody)
        insertNote(note)
    }


    private fun insertNote(note : Note)= viewModelScope.launch{
        repository.insert(note)
    }

    fun deleteNote(note : Note) = viewModelScope.launch{
            repository.delete(note)
    }

    fun searchNote(query : String?) : LiveData<List<Note>>{
        return repository.searchNote(query)
    }

    fun updateNote(note : Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

}