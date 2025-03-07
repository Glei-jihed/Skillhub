package edu.skillhub.repository

import edu.skillhub.network.ChatApiService
import edu.skillhub.network.ChatRequest
import edu.skillhub.network.ChatResponse

class ChatRepository(private val apiService: ChatApiService) {
    suspend fun sendMessage(userMessage: String, type: String): ChatResponse {
        val request = ChatRequest(message = userMessage, type = type)
        return apiService.sendMessage(request)
    }
}
