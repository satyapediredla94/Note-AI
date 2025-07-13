package com.example.noteaiagent.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteaiagent.domain.AddNoteUseCase
import com.example.noteaiagent.domain.QueryNotesUseCase
import com.example.noteaiagent.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val queryNotesUseCase: QueryNotesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    data class NoteState(
        val isLoading: Boolean = false,
        val response: String? = null
    )

    fun query(query: String, type: String) {
        val useCase = if (type == "Add") {
            addNotes(query)
        } else {
            queryNotes(query)
        }
        _state.value = state.value.copy(response = "")
        viewModelScope.launch(Dispatchers.IO) {
            useCase.collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }

                    is Resource.Success<*> -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            response = it.data.toString()
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun queryNotes(query: String) = queryNotesUseCase(query)
    private fun addNotes(query: String) = addNoteUseCase(query)
}