package com.bojanvilic.film.ui.models

data class Conversation(
    val image: String = "",
    val name: String = "",
    val previousMessageType: MessageType,
    val timestamp: String = "",
    val hasActiveStory: Boolean = false
)
