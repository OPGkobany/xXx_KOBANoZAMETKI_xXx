package com.example.calculator_idk2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.calculator_idk2.database.NoteDatabase
import com.example.calculator_idk2.repository.NoteRepository
import com.example.calculator_idk2.viewmodel.NoteViewModel
import com.example.calculator_idk2.viewmodel.NoteViewMpdelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        //инициализирует объект NoteRepository используя NoteDatabase
        val viewModelProviderFactory = NoteViewMpdelFactory(application, noteRepository)
        //инициализация класса
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
        //передача переменных и привязка всего
    }
}