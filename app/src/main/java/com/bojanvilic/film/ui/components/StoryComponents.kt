@file:OptIn(ExperimentalMaterial3Api::class)

package com.bojanvilic.film.ui.components

import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bojanvilic.film.R
import com.bojanvilic.film.ui.navigation.storyRoute
import kotlinx.coroutines.delay


@Composable
fun StoryScreen(
    storyUri: Uri,
    onImageClicked: () -> Unit,
    navController: NavController,
    steps: Int,
    currentStep: Int
) {

    Surface {
        StoryContent(
            onImageClicked = {
                onImageClicked()
            },
            storyUri = storyUri,
            navController = navController,
            steps = steps,
            currentStep = currentStep
        )
    }

    LaunchedEffect(Unit) {
        delay(24000)
        navController.popBackStack()
        navController.popBackStack()
        navController.popBackStack()
    }
}

@Composable
fun StoryContent(
    onImageClicked: () -> Unit,
    storyUri: Uri,
    navController: NavController,
    steps: Int,
    currentStep: Int
) {

    val goToNextScreen = {
        if (currentStep + 1 <= steps) navController.navigate("$storyRoute/$steps/${currentStep + 1}")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    goToNextScreen()
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        Column {
            InstagramSlicedProgressBar(steps, currentStep, false, goToNextScreen)

            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.lidija),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable {
                            onImageClicked()
                        })
                Text(
                    text = "Lidija Johnson", fontSize = 18.sp
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
            if (currentStep == 1) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(580.dp)
                        .padding(bottom = 16.dp),
                    painter = painterResource(id = R.drawable.story_1),
                    contentDescription = null,
                    contentScale = ContentScale.Fit

                )
            }
            if (currentStep == 2) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(580.dp)
                        .padding(bottom = 16.dp),
                    painter = painterResource(id = R.drawable.story_2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            if (currentStep == 3) {
                VideoView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(580.dp)
                        .padding(bottom = 16.dp)
                        .background(Color.Black), videoUri = storyUri.toString()
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
                        width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape
                    )
            ) {
                BasicTextField(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    value = "Send a message",
                    onValueChange = {})
            }
            Image(
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
            )
        }
    }
}