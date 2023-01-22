package com.bojanvilic.film.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bojanvilic.film.conversationList
import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.models.Message
import com.bojanvilic.film.ui.models.MessageType
import com.bojanvilic.film.ui.navigation.chatId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val chat: Flow<Conversation> =
        savedStateHandle.getStateFlow(chatId, initialValue = "0")
            .flatMapLatest { id ->
                flowOf(conversationList[id.toInt()])
            }

    var messageList by mutableStateOf(
        listOf(
            Message(
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Probna poruka sa jedne strane",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = true,
                text = "Druga poruka sa druge strane",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Nekako i slika da se namesti",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Nekako i slika da se namesti",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = true,
                text = "Druga poruka sa druge strane",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = true,
                text = "Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane ",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Probna poruka sa jedne strane",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane ",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ),
            Message(
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ),
        ))

    init {
        Handler(Looper.getMainLooper()).postDelayed({
            messageList = messageList.plus(Message(
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Najnovija poruka u vasem gradu!",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ))
        }, 3000)
    }
}