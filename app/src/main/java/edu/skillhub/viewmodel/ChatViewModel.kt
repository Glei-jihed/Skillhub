package edu.skillhub.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.skillhub.model.Message
import edu.skillhub.network.RetrofitClient
import edu.skillhub.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel(
    private val repository: ChatRepository = ChatRepository(RetrofitClient.apiService)
) : ViewModel() {


    val messages = mutableStateListOf<Message>()


    var isLoading = mutableStateOf(false)
        private set


    var errorMessage = mutableStateOf("")
        private set


    fun sendMessage(userMessage: String) {

        messages.add(Message(userMessage, isSent = true))


        val type = if (messages.size == 1) "start" else ""

        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = ""
            try {

                val response = repository.sendMessage(userMessage, type)

                messages.add(Message(response.response, isSent = false))
            } catch (e: Exception) {

                messages.add(Message("Erreur lors de l'envoi du message", isSent = false))
                errorMessage.value = e.message ?: "Erreur inconnue"
            } finally {
                isLoading.value = false
            }
        }
    }
}
