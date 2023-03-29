package com.bojanvilic.film.ui.models

import androidx.annotation.DrawableRes
import com.bojanvilic.film.R

data class Conversation(
    val id: Int = 0,
    @DrawableRes val image: Int = R.drawable.kitten,
    val name: String = "",
    val previousMessageText: String = "",
    val previousMessageType: MessageType = MessageType.Text,
    val timestamp: String = "",
    val hasActiveStory: Boolean = false,
    val hasUnreadMessage: Boolean = false,
    val isActive: Boolean = false,
    val messageList: List<Message> = listOf()
)
