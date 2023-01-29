package com.bojanvilic.film

import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.models.Message
import com.bojanvilic.film.ui.models.MessageType
import java.time.LocalDateTime

val conversationList: List<Conversation> = listOf(
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
)

var previewMessageList =
    listOf(
        Message(
            id = 0,
            messageType = MessageType.Text,
            isUserSender = false,
            text = "Probna poruka sa jedne strane",
            timestamp = LocalDateTime.of(2023, 1, 22, 13, 5),
            liked = true
        ),
        Message(
            id = 1,
            messageType = MessageType.Text,
            isUserSender = false,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            timestamp = LocalDateTime.of(2023, 1, 22, 13, 5),
            liked = true
        ),
    )