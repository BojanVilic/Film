package com.bojanvilic.film.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
    onImageClicked: () -> Unit
) {



    Surface {
        StoryContent(
            progressBarValue = storyViewModel.progressBarValue,
            progressBarValue2 = storyViewModel.progressBarValue2,
            onImageClicked = {
                onImageClicked()
            }
        )
    }
}

@Composable
fun StoryContent(
    progressBarValue: Float,
    progressBarValue2: Float,
    onImageClicked: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val progressAnimation by animateFloatAsState(
            targetValue = progressBarValue,
            animationSpec = tween(durationMillis = 8000, easing = LinearEasing),
        )
        val progressAnimation2 by animateFloatAsState(
            targetValue = progressBarValue2,
            animationSpec = tween(durationMillis = 8000, easing = LinearEasing),
        )

        Column {
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(end = 2.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    progress = progressAnimation
                )

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 2.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    progress = progressAnimation2
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kitten),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .clickable {
                            onImageClicked()
                        }
                )
                Text(
                    text = "Lidija Johnson"
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.primary),
                    painter = painterResource(id = R.drawable.ic_baseline_close_24),
                    contentDescription = null
                )
            }
        }
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
            0.5f,
            0.8f,
            onImageClicked = {}
        )
    }
}