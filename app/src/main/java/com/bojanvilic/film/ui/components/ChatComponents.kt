@file:OptIn(ExperimentalMaterial3Api::class)

package com.bojanvilic.film.ui.components

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
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

    ComposableLifecycle { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            chatViewModel.readMessage()
        }
    }

    Scaffold(
        topBar = {
            ChatTopBar(
                conversation = conversation.value,
                onBackClicked = {
                    onBackClicked()
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    value = "",
                    placeholder = { Text(text = "Search") },
                    onValueChange = {},
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = null
                        )
                    },
                    shape = CircleShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    ) { paddingValues ->
        ChatContent(
            paddingValues = paddingValues,
            conversation = conversation.value,
            messageList = messageList,
            onMessageLiked = {
                chatViewModel.updateMessageAfterLike(it)
            }
        )
    }
}

@Composable
fun ChatContent(
    paddingValues: PaddingValues,
    conversation: Conversation,
    messageList: List<Message>,
    onMessageLiked: (Int) -> Unit
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
            MessageItem(
                message = messageList[it],
                attachProfilePhoto = it > 0 && !messageList[it-1].isUserSender && messageList[it].isUserSender.not(),
                onMessageLiked = { messageId ->
                    onMessageLiked(messageId)
                }
            )
        }
    }
}

@Composable
fun MessageItem(
    message: Message,
    attachProfilePhoto: Boolean = false,
    onMessageLiked: (Int) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(0.8f)
                .align(if (message.isUserSender) Alignment.TopEnd else Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (message.isUserSender) Arrangement.End else Arrangement.Start
        ) {

            val messageModifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(if (message.isUserSender) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
                .padding(8.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            onMessageLiked(message.id)
                        }
                    )
                }

            var audioPlaying by remember {
                mutableStateOf(false)
            }

            Column(
                horizontalAlignment = Alignment.End
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
                    if (message.messageType == MessageType.Text) {
                        Text(
                            modifier = messageModifier,
                            text = message.text,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (message.isUserSender) Color.White else Color.Black
                        )
                    }
                    if (message.messageType == MessageType.Image) {
                        Image(
                            modifier = messageModifier,
                            painter = painterResource(id = R.drawable.kitten),
                            contentDescription = null
                        )
                    }
                    if (message.messageType == MessageType.VoiceMessage) {
                        Row(
                            modifier = messageModifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            val context = LocalContext.current

                            Image(
                                modifier = Modifier
                                    .height(48.dp),
                                painter = painterResource(id = R.drawable.audio_message),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                            )
                            Image(
                                modifier = Modifier
                                    .clickable {
                                        audioPlaying = !audioPlaying
                                        val mediaPlayer = MediaPlayer.create(context, R.raw.zvonkec)
                                        mediaPlayer.start()
                                    },
                                painter = painterResource(id = if (audioPlaying) R.drawable.ic_baseline_pause_24 else R.drawable.ic_baseline_play_arrow_24),
                                contentDescription = null
                            )
                        }
                    }
                }
                AnimatedVisibility(visible = message.liked) {
                    Image(
                        modifier = Modifier
                            .size(16.dp),
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null
                    )
                }
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
fun LeftSideMessagePreview() {
    AppTheme {
        MessageItem(
            message = previewMessageList[1],
            onMessageLiked = {}
        )
    }
}
