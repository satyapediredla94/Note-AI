package com.example.noteaiagent.domain

import com.example.noteaiagent.data.PromptType
import kotlinx.coroutines.flow.Flow

interface AIAgentRepo {
    suspend fun queryUsingKoog(
        query: String,
        promptType: PromptType
    ): Flow<Resource>
}