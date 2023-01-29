package com.bojanvilic.film

import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.models.Message
import com.bojanvilic.film.ui.models.MessageType
import java.time.LocalDateTime

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