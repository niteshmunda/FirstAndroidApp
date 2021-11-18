package com.example.notes_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application){
    var allnotes:List<Notes>
    val repository:NoteRespository

    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRespository(dao)
        allnotes=repository.allNotes
    }
    fun deleteNote (note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }

    fun updateNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }

    fun addNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note)
    }
}