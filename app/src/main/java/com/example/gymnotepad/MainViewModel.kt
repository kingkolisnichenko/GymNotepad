package com.example.gymnotepad

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val mNotes = mutableStateListOf<NoteModel>()

    val notes: List<NoteModel>
        get() = mNotes

    fun addNote(note: NoteModel) {
        mNotes.add(note)
    }

}