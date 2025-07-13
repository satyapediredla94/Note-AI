package com.example.noteaiagent.data.repo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val title: String,
    val content: String,
    val tags: List<String>? = null,
    val timestamp: Long = System.currentTimeMillis()
)
