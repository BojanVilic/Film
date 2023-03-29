@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
//        StoryContent(
//            progressBarValue = storyViewModel.progressBarValue,
//            progressBarValue2 = storyViewModel.progressBarValue2,
//            onImageClicked = {
//                onImageClicked()
//            }
//        )

        VideoView(videoUri = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
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
        }
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.kitten),
            contentDescription = null
        )
        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                value = "",
                placeholder = { Text(text = "Send message", color = Color.DarkGray) },
                onValueChange = {  },
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
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
            onImageClicked = {}
        )
    }
}