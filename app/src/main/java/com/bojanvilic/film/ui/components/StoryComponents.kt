package com.bojanvilic.film.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bojanvilic.film.R
import com.bojanvilic.film.theme.AppTheme
import com.bojanvilic.film.ui.StoryViewModel

@Composable
fun StoryScreen(
    storyViewModel: StoryViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {

    Surface {
        StoryContent(
            progressBarValue = storyViewModel.progressBarValue
        )
    }
}

@Composable
fun StoryContent(
    progressBarValue: Float
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val progressAnimation by animateFloatAsState(
            targetValue = progressBarValue,
            animationSpec = tween(durationMillis = 10000, easing = LinearEasing),
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            progress = progressAnimation
        )

        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.kitten),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun StoryContentPreview() {
    AppTheme {
        StoryContent(
            0.5f
        )
    }
}