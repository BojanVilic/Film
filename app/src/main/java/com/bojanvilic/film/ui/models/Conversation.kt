package com.bojanvilic.film.ui.models

data class Conversation(
    val image: String = "",
    val name: String = "",
    val previousMessageText: String = "",
    val previousMessageType: MessageType,
    val timestamp: String = "",
    val hasActiveStory: Boolean = false,
    val hasUnreadMessage: Boolean = false,
    val isActive: Boolean = false
)
