package com.bojanvilic.film.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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
                id = 0,
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Probna poruka sa jedne strane",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ),
            Message(
                id = 1,
                messageType = MessageType.Image,
                isUserSender = true,
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                id = 2,
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Nekako i slika da se namesti",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                id = 3,
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Nekako i slika da se namesti",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                id = 4,
                messageType = MessageType.Text,
                isUserSender = true,
                text = "Druga poruka sa druge strane",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                id = 5,
                messageType = MessageType.Text,
                isUserSender = true,
                text = "Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane Druga poruka sa druge strane ",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 6)
            ),
            Message(
                id = 6,
                messageType = MessageType.Text,
                isUserSender = false,
                text = "Probna poruka sa jedne strane",
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ),
            Message(
                id = 7,
                messageType = MessageType.Image,
                isUserSender = false,
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ),
            Message(
                id = 8,
                messageType = MessageType.Image,
                isUserSender = false,
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            )
        ))

    var conversationList: List<Conversation> by mutableStateOf(listOf(
        Conversation(
            id = 0,
            image = "",
            name = "Tanja Bošković",
            previousMessageType = MessageType.Image,
            timestamp = "16:01",
            hasUnreadMessage = true,
            isActive = true
        ),
        Conversation(
            id = 1,
            image = "",
            name = "Nino",
            previousMessageType = MessageType.Text,
            timestamp = "Fri",
            previousMessageText = "Eo me."
        ),
        Conversation(
            id = 2,
            image = "",
            name = "Irina Dujaković",
            previousMessageType = MessageType.Image,
            timestamp = "Thu",
        ),
        Conversation(
            id = 3,
            image = "",
            name = "Lidija Johnson",
            previousMessageType = MessageType.Text,
            timestamp = "16. Dec",
            hasUnreadMessage = false,
            isActive = true,
            previousMessageText = "Kako je u...",
            hasActiveStory = true
        ),
        Conversation(
            id = 4,
            name = "Snežana Maletin",
            previousMessageType = MessageType.Text,
            timestamp = "1. Dec",
            hasActiveStory = false,
            isActive = true,
            previousMessageText = "Hvala!! <3"
        ),
        Conversation(
            id = 5,
            name = "Rastko Popović",
            previousMessageType = MessageType.Text,
            timestamp = "Nekada davno",
            previousMessageText = "Ova poruka mozda...",
            hasActiveStory = true
        ),
        Conversation(
            id = 6,
            image = "",
            name = "Bole",
            previousMessageType = MessageType.Image,
            timestamp = "21:35",
            hasActiveStory = false,
            isActive = true
        ),
        Conversation(
            id = 7,
            image = "",
            name = "Mile",
            previousMessageType = MessageType.Text,
            timestamp = "22:25",
            previousMessageText = "Poslao sam ti poruku."
        ),
        Conversation(
            id = 8,
            image = "",
            name = "Bole",
            previousMessageType = MessageType.Image,
            timestamp = "21:35",
            hasActiveStory = true,
            isActive = true
        ),
        Conversation(
            id = 9,
            image = "",
            name = "Mile",
            previousMessageType = MessageType.Text,
            timestamp = "22:25",
            previousMessageText = "Poslao sam ti poruku."
        )
    ))

    init {
        Handler(Looper.getMainLooper()).postDelayed({
            messageList = messageList.plus(Message(
                id = 9,
                messageType = MessageType.VoiceMessage,
                isUserSender = false,
                timestamp = LocalDateTime.of(2023, 1, 22, 13, 5)
            ))

            conversationList = conversationList.map {
                if (it.id == 0) {
                    it.copy(previousMessageType = MessageType.VoiceMessage)
                } else {
                    it
                }
            }
        }, 3000)
    }

    fun updateMessageAfterLike(id: Int) {
        messageList = messageList.map {
            if (it.id == id) {
                it.copy(liked = !it.liked)
            } else {
                it
            }
        }
    }

    fun readMessage() {
        conversationList = conversationList.map {
            if (it.id == 0) {
                it.copy(hasUnreadMessage = false)
            } else {
                it
            }
        }
    }
}