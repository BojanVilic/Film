package com.bojanvilic.film.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
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
    paddingValues: PaddingValues,
    conversationsList: List<Conversation>
) {
    Surface(
        modifier = Modifier.padding(paddingValues)
    ) {
        LazyColumn {
            items(count = conversationsList.size) {
                ConversationsListItem(conversationsList[it])
            }
        }
    }
}

@Composable
fun ConversationsListItem(conversation: Conversation) {

    val defaultModifier = Modifier
        .size(64.dp)
        .clip(CircleShape)

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color(0xff157d76),
        targetValue = Color(0xff13baae),
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.kitten),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = if (conversation.hasActiveStory)
                defaultModifier
                    .border(3.dp, color, CircleShape)
                    .clickable {

                    } else defaultModifier
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
                image = "",
                name = "Tanja Boskovic",
                previousMessageType = MessageType.VoiceMessage,
                timestamp = "15:30",
                hasActiveStory = true
            )
        )
    }
}