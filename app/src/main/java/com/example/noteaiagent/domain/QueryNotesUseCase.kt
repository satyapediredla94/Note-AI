package com.example.noteaiagent.domain

import com.example.noteaiagent.data.PromptType
import com.example.noteaiagent.data.repo.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QueryNotesUseCase @Inject constructor(
    private val aiAgentRepo: AIAgentRepo,
    private val noteRepository: NoteRepository
) {
    operator fun invoke(query: String): Flow<Resource> = flow {
        emit(Resource.Loading)
        val notes = noteRepository.getAllNotes()
        val enquire = """
            user: $query
            Answer the question based on the context below.
            ${notes.joinToString(", ")}
        """.trimIndent()
        aiAgentRepo.queryUsingKoog(enquire, PromptType.QUERY).collect {
            if (it is Resource.Success<*> && it.data is Note) {
                noteRepository.insertNote(it.data)
            } else if (it is Resource.Success<*>) {
                emit(Resource.Error("No notes found"))
            }
            emit(it)
        }
    }
}