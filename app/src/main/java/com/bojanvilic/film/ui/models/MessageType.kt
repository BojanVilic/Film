package com.bojanvilic.film.ui.models

enum class MessageType(val stringValue: String) {
    Text("a text message"),
    Image("a photo"),
    VoiceMessage("a voice message")
}