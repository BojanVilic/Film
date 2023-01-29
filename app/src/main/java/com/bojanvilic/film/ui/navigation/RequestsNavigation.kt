package com.bojanvilic.film.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.film.ui.ChatViewModel
import com.bojanvilic.film.ui.TopLevelDestinations
import com.bojanvilic.film.ui.components.ConversationsList

fun NavGraphBuilder.requestsScreen(navController: NavHostController, chatViewModel: ChatViewModel) {
    composable(TopLevelDestinations.Requests.route) {
        ConversationsList(
            conversationsList = chatViewModel.conversationList,
            onChatClicked = { chatId ->
                navController.navigate("$chatRoute/$chatId")
            },
            onStoryClicked = {
                navController.navigate(storyRoute)
            }
        )
    }
}