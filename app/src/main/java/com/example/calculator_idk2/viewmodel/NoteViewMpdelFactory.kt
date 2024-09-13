package com.example.calculator_idk2.viewmodel

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator_idk2.repository.NoteRepository

class NoteViewMpdelFactory (val app: Application, private val noteRepository: NoteRepository):ViewModelProvider.Factory {
    //класс инициализирует и возвращает вьюмодел

    override fun <T:ViewModel> create(modelClass:Class<T>): T {
        /*функция необходима для создания вью модела, она переписывает метод из провайдера ViewModel
         интерфейса Factory. Внутри она возврашает новую инстанцию вью модел, передавая app и
         noteRepository в его констурктор
         */
        return NoteViewModel(app, noteRepository) as T
        //as T в конце удостоверяет что тип возращаемого объекта такой же, какой и задан в начале функции
    }
}