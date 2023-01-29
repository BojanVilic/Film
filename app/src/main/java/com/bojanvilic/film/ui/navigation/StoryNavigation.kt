package com.bojanvilic.film.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.film.ui.ChatViewModel
import com.bojanvilic.film.ui.components.StoryScreen

const val storyRoute = "story_route"

fun NavGraphBuilder.storyScreen(navController: NavHostController, chatViewModel: ChatViewModel) {
    composable(
        route = storyRoute,
    ) {
        StoryScreen(onImageClicked = {
            chatViewModel.setChatId("3")
            navController.navigate("$chatRoute/3")
        })
    }
}