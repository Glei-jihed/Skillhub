package edu.skillhub.repository

import edu.skillhub.network.ChatApiService
import edu.skillhub.network.ChatRequest
import edu.skillhub.network.ChatResponse
import edu.skillhub.network.Summary

class ChatRepository(private val apiService: ChatApiService) {
    suspend fun sendMessage(userMessage: String, type: String): ChatResponse {
        val summary = Summary(summary = userMessage)
        val request = ChatRequest(message = userMessage, type = type, summary = summary)
        return apiService.sendMessage(request)
    }
}
