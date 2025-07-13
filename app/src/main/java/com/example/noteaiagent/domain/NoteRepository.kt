package com.example.noteaiagent.domain

import com.example.noteaiagent.data.repo.Note

interface NoteRepository {
    suspend fun insertNote(note: Note): Long
    suspend fun getNoteById(id: Long): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNote(note: Note)
}