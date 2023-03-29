package com.bojanvilic.film.ui.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.film.ui.ChatViewModel
import com.bojanvilic.film.ui.components.StoryScreen

const val storyRoute = "story_route"

fun NavGraphBuilder.storyScreen(navController: NavHostController, chatViewModel: ChatViewModel, storyUri: Uri) {
    composable(
        route = storyRoute,
    ) {
        StoryScreen(
            storyUri = storyUri,
            onImageClicked = {
                chatViewModel.setChatId("3")
                navController.navigate("$chatRoute/3")
            },
            navController = navController
        )
    }
}