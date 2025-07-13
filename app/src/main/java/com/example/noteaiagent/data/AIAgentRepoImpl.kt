package com.example.noteaiagent.data

import ai.koog.agents.core.agent.AIAgent
import com.example.noteaiagent.data.repo.Note
import com.example.noteaiagent.domain.AIAgentRepo
import com.example.noteaiagent.domain.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AIAgentRepoImpl @Inject constructor(
    private val koogAgent: KoogAgent
) : AIAgentRepo {
    override suspend fun queryUsingKoog(
        query: String,
        promptType: PromptType
    ): Flow<Resource> = flow {
        emit(Resource.Loading)
        val aiAgent = koogAgent.initializeKoogAgent(promptType)
        val response = aiAgent.runAndGetResult(query).orEmpty()
        emit(
            Resource.Success(
                if (response.startsWith("{")) {
                    val note = Gson().fromJson(response.formatResponse(), Note::class.java)
                    note
                } else {
                    response
                }
            )
        )
    }

    private fun String.formatResponse(): String {
        /*
        Response is in the following format:
            {
              "title": "Lost pen",
              "content": "My pen is in red box",
              "tags": [
                "pen",
                "lost",
                "box"
              ]
            }
         */
        return replace("\n", "").trim()
    }
}