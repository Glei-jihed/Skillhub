package edu.skillhub.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.skillhub.model.Message

@Composable
fun ChatScreen() {

    val messages = remember { mutableStateListOf<Message>() }
    var textState by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {

            items(messages.reversed()) { message ->
                ChatMessageItem(message)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Votre message") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (textState.isNotBlank()) {

                        messages.add(Message(textState, isSent = true))
                        textState = ""
                        // TODO : Intégrer Retrofit pour envoyer le message et récupérer la réponse du chatbot
                    }
                }
            ) {
                Text("Envoyer")
            }
        }
    }
}

@Composable
fun ChatMessageItem(message: Message) {

    val alignment = if (message.isSent) Alignment.CenterEnd else Alignment.CenterStart
    val backgroundColor = if (message.isSent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = message.content,
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            color = Color.White
        )
    }
}
