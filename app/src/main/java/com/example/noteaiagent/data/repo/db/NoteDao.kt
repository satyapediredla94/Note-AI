package com.example.noteaiagent.data.repo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteaiagent.data.repo.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Long): Note?

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)
}