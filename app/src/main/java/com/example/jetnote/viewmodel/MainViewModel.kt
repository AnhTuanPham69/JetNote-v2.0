package com.example.jetnote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.repository.Repository
import com.example.jetnote.domain.model.NoteModel
import com.example.jetnote.routing.JetNotesRouter
import com.example.jetnote.routing.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * View model used for storing the global app state.
 *
 * This view model is used for all screens.
 */
class MainViewModel(private val repository: Repository) : ViewModel() {


    val notesNotInTrash : LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash()
    }

    fun onCreateNewNoteClick(){
        JetNotesRouter.navigateTo(Screen.SaveNote)
    }

    fun onNoteClick(note: NoteModel){
        _noteEntry.value = note
        JetNotesRouter.navigateTo(Screen.SaveNote)
    }

    fun onNoteCheckedChange(note:NoteModel){
        viewModelScope.launch(Dispatchers.Default){
            repository.insertNote(note)
        }
    }

    fun onNoteEntryChange(note: NoteModel){
        _noteEntry.value = note
    }

    fun saveNote(note: NoteModel){
        viewModelScope.launch(Dispatchers.Default){
            repository.insertNote(note)
            withContext(Dispatchers.Main){
                JetNotesRouter.navigateTo(Screen.Notes)

                _noteEntry.value = NoteModel()
            }

        }
    }

    fun moveNoteToTrash(note: NoteModel){
        viewModelScope.launch(Dispatchers.Default){
            repository.moveNoteToTrash(note.id)

            withContext(Dispatchers.Main){
                JetNotesRouter.navigateTo(Screen.Notes)
            }
        }
    }
}
