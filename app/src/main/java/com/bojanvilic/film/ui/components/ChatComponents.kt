@file:OptIn(ExperimentalMaterial3Api::class)

package com.bojanvilic.film.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bojanvilic.film.R
import com.bojanvilic.film.previewMessageList
import com.bojanvilic.film.theme.AppTheme
import com.bojanvilic.film.ui.ChatViewModel
import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.models.Message
import com.bojanvilic.film.ui.models.MessageType

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val conversation = chatViewModel.chat.collectAsState(initial = Conversation())
    val messageList = chatViewModel.messageList

    Scaffold(
        topBar = {
            ChatTopBar(
                conversation = conversation.value,
                onBackClicked = {
                    onBackClicked()
                }
            )
        }
    ) { paddingValues ->
        ChatContent(
            paddingValues = paddingValues,
            conversation = conversation.value,
            messageList = messageList
        )
    }
}

@Composable
fun ChatContent(
    paddingValues: PaddingValues,
    conversation: Conversation,
    messageList: List<Message>
) {
    val listState = rememberLazyListState()
    LaunchedEffect(messageList.size) {
        listState.animateScrollToItem(messageList.size)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        state = listState
    ) {
        items(messageList.size) {
            if (messageList[it].isUserSender)
                RightSideMessage(message = messageList[it])
            else
                LeftSideMessage(
                    message = messageList[it],
                    attachProfilePhoto = it > 0 && !messageList[it-1].isUserSender
                )
        }
    }
}

@Composable
fun LeftSideMessage(
    message: Message,
    attachProfilePhoto: Boolean = false
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(0.8f)
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                if (attachProfilePhoto) {
                    Image(
                        painter = painterResource(id = R.drawable.kitten),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(36.dp)
                    )
                }
                Text(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.onSurfaceVariant)
                        .padding(8.dp),
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )

            }
        }
    }
}

@Composable
fun RightSideMessage(message: Message) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 4.dp)
                .align(Alignment.TopEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ChatTopBar(
    conversation: Conversation,
    onBackClicked: () -> Unit
) {
    val brush = Brush.horizontalGradient(
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    val infiniteTransition = rememberInfiniteTransition()
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable {
                    onBackClicked()
                },
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Image(
                painter = painterResource(id = R.drawable.kitten),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .padding(4.dp)
                    .drawBehind {
                        if (conversation.hasActiveStory) {
                            rotate(rotateAnimation) {
                                drawCircle(brush, style = Stroke(20f))
                            }
                        }
                    }
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.weight(0.4f))
            Column {
                Row {
                    Text(
                        text = conversation.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    if (conversation.isActive) {
                        Canvas(
                            modifier = Modifier
                                .align(Alignment.Top)
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                .size(6.dp),
                            onDraw = {
                                drawCircle(color = Color(0xFF9bbc3c))
                            })
                    }
                }
                if (conversation.isActive) {
                    Text(
                        text = "Active now",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(0.3f))
            Icon(
                painter = painterResource(id = R.drawable.ic_video),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun ChatTopBarPreview() {
    AppTheme {
        ChatTopBar(
            conversation = Conversation(
                image = "",
                name = "Tanja Boskovic",
                previousMessageType = MessageType.VoiceMessage,
                timestamp = "15:30",
                hasActiveStory = true,
                isActive = true
            ),
            onBackClicked = {}
        )
    }
}
@Preview
@Composable
fun RightSideMessagePreview() {
    AppTheme {
        RightSideMessage(
            message = previewMessageList[0]
        )
    }
}

@Preview
@Composable
fun LeftSideMessagePreview() {
    AppTheme {
        LeftSideMessage(
            message = previewMessageList[1]
        )
    }
}
