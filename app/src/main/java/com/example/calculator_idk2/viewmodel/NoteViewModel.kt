package com.example.calculator_idk2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator_idk2.model.Note
import com.example.calculator_idk2.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository):AndroidViewModel(app) {

    fun addNote(note: Note) = viewModelScope.launch {
        //запуск корутины для аддноут и её отмена когда вьюмодел очищается или уничтожается
        noteRepository.insertNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun  getAllNotes() = noteRepository.getAllNotes()
    //получение данных, чтобы не передавать параметры

    fun searchNote(query: String?) = noteRepository.searchNote(query)
    //поиск заметок


}