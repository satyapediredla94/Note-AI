package com.example.noteaiagent.data

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.llms.all.simpleOllamaAIExecutor
import ai.koog.prompt.llm.OllamaModels

class KoogAgent {

    fun initializeKoogAgent(promptType: PromptType): AIAgent {
        val aiAgent = AIAgent(
            executor = simpleOllamaAIExecutor(baseUrl = "http://10.0.2.2:11434"),
            systemPrompt = getPrompt(promptType),
            llmModel = OllamaModels.Meta.LLAMA_3_2
        )
        return aiAgent
    }

    private fun getPrompt(promptType: PromptType): String {
        when (promptType) {
            PromptType.QUERY -> {
                return """
                     Analyze user's query and search for notes that match the query. Once you find the match,
                     create a summary of 1-2 lines which answers user query. The list of notes will be sent as a part of prompt.
                     Do not provide any information regarding notes and note ids.
                     Example:
                     Summary: It seems you've lost your pen and it's currently stored in a red box.
                     Please provide more context or another query to search for related notes.
                """.trimIndent()
            }

            PromptType.ADD -> {
                return """
                    Analyze user's query and check the list of notes provided. If nothing found regarding the query, then create create a note object in JSON format which contains the following fields:
                 title, content, tags. Create tags as a list of strings. Number of tags ranges from 1-5.
                 The response should be created as a note just return JSON response and nothing else. 
                 Example:
                 {
                   "title": "Lost pen",
                   "content": "My pen is in red box",
                   "tags": [
                     "pen",
                     "lost",
                     "box"
                   ]
                 }
                 
                  No other information except the json response.
                """.trimIndent()
            }

        }
    }


}

enum class PromptType {
    QUERY,
    ADD
}