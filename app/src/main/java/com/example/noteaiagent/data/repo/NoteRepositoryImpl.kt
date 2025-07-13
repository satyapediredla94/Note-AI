package com.example.noteaiagent.data.repo

import com.example.noteaiagent.data.repo.db.NoteDao
import com.example.noteaiagent.domain.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(note)
    }

    override suspend fun getNoteById(id: Long): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    override suspend fun deleteNote(note: Note) {
        note.id?.let {
            noteDao.deleteNote(it)
        }
    }

}