package com.bojanvilic.film.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.film.conversationList
import com.bojanvilic.film.ui.TopLevelDestinations
import com.bojanvilic.film.ui.components.ConversationsList

fun NavGraphBuilder.generalScreen(navController: NavHostController) {
    composable(TopLevelDestinations.General.route) {
        ConversationsList(
            conversationsList = conversationList,
            onChatClicked = { chatId ->
                navController.navigate("$chatRoute/$chatId")
            }
        )
    }
}