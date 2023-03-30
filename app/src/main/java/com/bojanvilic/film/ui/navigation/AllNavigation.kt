package com.bojanvilic.film.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.film.ui.ChatViewModel
import com.bojanvilic.film.ui.TopLevelDestinations
import com.bojanvilic.film.ui.components.ConversationsList

fun NavGraphBuilder.allScreen(navController: NavHostController, chatViewModel: ChatViewModel) {
    composable(TopLevelDestinations.All.route) {
        ConversationsList(
            conversationsList = chatViewModel.conversationList,
            onChatClicked = { chatId ->
                chatViewModel.setChatId(chatId.toString())
                navController.navigate("$chatRoute/$chatId")
            },
            onStoryClicked = {
                navController.navigate("$storyRoute/3/1")
            }
        )
    }
}