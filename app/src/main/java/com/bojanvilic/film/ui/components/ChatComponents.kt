package com.bojanvilic.film.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bojanvilic.film.ui.ChatViewModel

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    Text(text = "Dobrodosao")
}