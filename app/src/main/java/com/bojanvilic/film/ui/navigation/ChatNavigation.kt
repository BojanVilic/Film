package com.bojanvilic.film.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bojanvilic.film.ui.ChatViewModel
import com.bojanvilic.film.ui.components.ChatScreen

const val chatRoute = "chat_route"
const val chatId = "chatId"

fun NavGraphBuilder.chatScreen(onBackClicked: () -> Unit, chatViewModel: ChatViewModel) {
    composable(
        route = "$chatRoute/{$chatId}",
        arguments = listOf(navArgument(chatId) { type = NavType.StringType })
    ) {
        ChatScreen(onBackClicked = onBackClicked, chatViewModel = chatViewModel)
    }
}