package com.bojanvilic.film.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bojanvilic.film.R
import com.bojanvilic.film.theme.AppTheme
import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.models.MessageType

@Composable
fun ConversationsList(
    conversationsList: List<Conversation>,
    onChatClicked: (Int) -> Unit,
    onStoryClicked: () -> Unit
) {
    Surface {
        LazyColumn(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(count = conversationsList.size) {
                ConversationsListItem(
                    conversation = conversationsList[it],
                    onChatClicked = { chatId ->
                        onChatClicked(chatId)
                    },
                    onStoryClicked = {
                        onStoryClicked()
                    }
                )
            }
        }
    }
}

@Composable
fun ConversationsListItem(
    conversation: Conversation,
    onChatClicked: (Int) -> Unit,
    onStoryClicked: () -> Unit
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

    val defaultImageModifier = Modifier
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

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onChatClicked(conversation.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = conversation.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = if (conversation.hasActiveStory)
                defaultImageModifier.clickable {
                    onStoryClicked()
                } else defaultImageModifier
        )
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text(
                    text = conversation.name,
                    style = MaterialTheme.typography.titleMedium
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
            Row(verticalAlignment = Alignment.CenterVertically) {

                val previousMessageText =
                    if (conversation.previousMessageType == MessageType.VoiceMessage ||
                        conversation.previousMessageType == MessageType.Image) {
                        "Sent ${conversation.previousMessageType.stringValue}." + " "
                    } else {
                        conversation.previousMessageText + " "
                    }

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (conversation.hasUnreadMessage) FontWeight.Black else FontWeight.Normal,
                    text = previousMessageText
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (conversation.hasUnreadMessage) FontWeight.Black else FontWeight.Normal,
                    text = conversation.timestamp
                )
            }
        }
    }
}

@Preview
@Composable
fun ConversationsListItemPreview() {
    AppTheme {
        ConversationsListItem(
            conversation = Conversation(
                image = R.drawable.kitten,
                name = "Tanja Boskovic",
                previousMessageType = MessageType.VoiceMessage,
                timestamp = "15:30",
                hasActiveStory = true
            ),
            onChatClicked = {},
            onStoryClicked = {}
        )
    }
}