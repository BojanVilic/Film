package com.bojanvilic.film.ui.models

import androidx.annotation.DrawableRes
import com.bojanvilic.film.R
import java.time.LocalDateTime

data class Message(
    val id: Int,
    val messageType: MessageType,
    val isUserSender: Boolean = false,
    val text: String = "",
    @DrawableRes val image: Int = R.drawable.kitten,
    val voiceMessage: String = "",
    val timestamp: LocalDateTime? = null,
    val liked: Boolean = false,
    val seen: Boolean = false
)