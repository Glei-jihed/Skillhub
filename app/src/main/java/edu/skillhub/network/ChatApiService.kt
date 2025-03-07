package edu.skillhub.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {
    @Headers("Content-Type: application/json")
    @POST("chatbot/chatbot")
    suspend fun sendMessage(@Body request: ChatRequest): ChatResponse
}
