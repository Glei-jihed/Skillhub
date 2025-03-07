package edu.skillhub.network


data class Summary(
    val summary: String
)

data class ChatRequest(
    val message: String,
    val type: String,
    val summary: Summary
)


data class ChatResponse(
    val response: String
)
