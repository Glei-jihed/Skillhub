package edu.skillhub.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.skillhub.model.Message
import edu.skillhub.network.ChatRequest
import edu.skillhub.network.RetrofitClient
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    // Liste observable des messages
    val messages = mutableStateListOf<Message>()

    // Fonction pour envoyer un message et ajouter la réponse du chatbot
    fun sendMessage(userMessage: String) {
        // Ajoute le message de l'utilisateur
        messages.add(Message(userMessage, isSent = true))

        viewModelScope.launch {
            try {
                // Appel API en utilisant Retrofit (la fonction est suspend)
                val response = RetrofitClient.apiService.sendMessage(ChatRequest(userMessage))
                // Ajoute la réponse du chatbot
                messages.add(Message(response.response, isSent = false))
            } catch (e: Exception) {
                // En cas d'erreur, affiche un message d'erreur
                messages.add(Message("Erreur lors de l'envoi du message", isSent = false))
            }
        }
    }
}
