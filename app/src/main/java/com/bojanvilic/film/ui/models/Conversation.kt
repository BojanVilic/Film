package com.bojanvilic.film.ui.models

data class Conversation(
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val previousMessageText: String = "",
    val previousMessageType: MessageType = MessageType.Text,
    val timestamp: String = "",
    val hasActiveStory: Boolean = false,
    val hasUnreadMessage: Boolean = false,
    val isActive: Boolean = false
)
