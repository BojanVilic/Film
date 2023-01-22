package com.bojanvilic.film.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bojanvilic.film.ui.components.StoryScreen

const val storyRoute = "story_route"

fun NavGraphBuilder.storyScreen(onBackClicked: () -> Unit) {
    composable(
        route = storyRoute,
    ) {
        StoryScreen(onBackClicked = onBackClicked)
    }
}