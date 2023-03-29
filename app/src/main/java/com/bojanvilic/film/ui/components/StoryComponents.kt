@file:OptIn(ExperimentalMaterial3Api::class)

package com.bojanvilic.film.ui.components

import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bojanvilic.film.R
import com.bojanvilic.film.theme.AppTheme
import com.bojanvilic.film.ui.StoryViewModel
import kotlinx.coroutines.delay


@Composable
fun StoryScreen(
    storyUri: Uri,
    storyViewModel: StoryViewModel = hiltViewModel(),
    onImageClicked: () -> Unit,
    navController: NavController
) {

    Surface {
        StoryContent(
            progressBarValue = storyViewModel.progressBarValue,
            progressBarValue2 = storyViewModel.progressBarValue2,
            progressBarValue3 = storyViewModel.progressBarValue3,
            onImageClicked = {
                onImageClicked()
            },
            storyUri = storyUri,
            onStoryClicked = {
            }
        )
    }

    LaunchedEffect(Unit) {
        delay(18500)
        navController.popBackStack()
    }
}

@Composable
fun StoryContent(
    progressBarValue: Float,
    progressBarValue2: Float,
    progressBarValue3: Float,
    onImageClicked: () -> Unit,
    storyUri: Uri,
    onStoryClicked: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val progressAnimation by animateFloatAsState(
            targetValue = progressBarValue,
            animationSpec = tween(durationMillis = 5000, easing = LinearEasing)
        )
        val progressAnimation2 by animateFloatAsState(
            targetValue = progressBarValue2,
            animationSpec = tween(durationMillis = 5000, easing = LinearEasing)
        )
        val progressAnimation3 by animateFloatAsState(
            targetValue = progressBarValue3,
            animationSpec = tween(durationMillis = 8000, easing = LinearEasing)
        )

        Column {
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 2.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    progress = progressAnimation
                )

                LinearProgressIndicator(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    progress = progressAnimation2
                )

                LinearProgressIndicator(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    progress = progressAnimation3
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lidija),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable {
                            onImageClicked()
                        }
                )
                Text(
                    text = "Lidija Johnson",
                    fontSize = 18.sp
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
            if (progressAnimation != 0f && progressAnimation != 1f) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(580.dp)
                        .padding(bottom = 16.dp)
                        .clickable {
                            onStoryClicked()
                        },
                    painter = painterResource(id = R.drawable.story_1),
                    contentDescription = null,
                    contentScale = ContentScale.Fit

                )
            }
            if (progressAnimation2 != 0f && progressAnimation2 != 1f) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(580.dp)
                        .padding(bottom = 16.dp)
                        .clickable {
                            onStoryClicked()
                        },
                    painter = painterResource(id = R.drawable.story_2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            if (progressAnimation3 != 0f && progressAnimation3 != 1f) {
                VideoView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(580.dp)
                        .padding(bottom = 16.dp)
                        .background(Color.Black),
                    videoUri = storyUri.toString()
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            ) {
                BasicTextField(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    value = "Send a message",
                    onValueChange = {}
                )
            }
            Image(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Preview
@Composable
fun StoryContentPreview() {
    AppTheme {
        StoryContent(
            0.5f,
            0.8f,
            0.5f,
            onImageClicked = {},
            storyUri = Uri.EMPTY,
            onStoryClicked = {}
        )
    }
}