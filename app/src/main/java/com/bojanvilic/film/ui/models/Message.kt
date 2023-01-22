package com.bojanvilic.film.ui.models

import java.time.LocalDateTime

data class Message(
    val messageType: MessageType,
    val isUserSender: Boolean = false,
    val text: String = "",
    val image: String = "",
    val voiceMessage: String = "",
    val timestamp: LocalDateTime = LocalDateTime.now()
)