package edu.skillhub.network

data class ChatRequest(
    val message: String,
    val type: String
)

data class ChatResponse(
    val summary: String
)
