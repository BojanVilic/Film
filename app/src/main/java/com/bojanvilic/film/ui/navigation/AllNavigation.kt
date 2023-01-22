package com.bojanvilic.film.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.film.conversationList
import com.bojanvilic.film.ui.TopLevelDestinations
import com.bojanvilic.film.ui.components.ConversationsList

fun NavGraphBuilder.allScreen(navController: NavHostController) {
    composable(TopLevelDestinations.All.route) {
        ConversationsList(
            conversationsList = conversationList,
            onChatClicked = { chatId ->
                navController.navigate("$chatRoute/$chatId")
            },
            onStoryClicked = {
                navController.navigate(storyRoute)
            }
        )
    }
}