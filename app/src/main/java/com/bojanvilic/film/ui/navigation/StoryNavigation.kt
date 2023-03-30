package com.bojanvilic.film.ui.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bojanvilic.film.ui.ChatViewModel
import com.bojanvilic.film.ui.components.StoryScreen

const val storyRoute = "story_route"

fun NavGraphBuilder.storyScreen(navController: NavHostController, chatViewModel: ChatViewModel, storyUri: Uri) {
    composable(
        "$storyRoute/{steps}/{currentStep}",
        arguments = listOf(
            navArgument("steps") { type = NavType.IntType; defaultValue = 3 },
            navArgument("currentStep") { type = NavType.IntType; defaultValue = 1 },
        )
    ) { backStackEntry ->
        StoryScreen(
            storyUri = storyUri,
            onImageClicked = {
                chatViewModel.setChatId("3")
                navController.navigate("$chatRoute/3")
            },
            steps = backStackEntry.arguments!!.getInt("steps"),
            currentStep = backStackEntry.arguments!!.getInt("currentStep"),
            navController = navController
        )
    }
}