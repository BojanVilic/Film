package com.bojanvilic.film.ui.models

data class Message(
    val messageType: MessageType,
    val text: String = "",
    val image: String
)