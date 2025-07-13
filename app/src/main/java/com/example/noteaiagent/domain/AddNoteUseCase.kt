package com.example.noteaiagent.domain

import com.example.noteaiagent.data.PromptType
import com.example.noteaiagent.data.repo.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val agentRepo: AIAgentRepo,
    private val noteRepository: NoteRepository
) {
    operator fun invoke(note: String): Flow<Resource> = flow {
        emit(Resource.Loading)
        agentRepo.queryUsingKoog(note, PromptType.ADD).collect {
            if (it is Resource.Success<*> && it.data is Note) {
                noteRepository.insertNote(it.data)
                emit(Resource.Success("Successfully added note"))
            } else {
                emit(Resource.Error("Failed to add note"))
            }
        }
    }
}