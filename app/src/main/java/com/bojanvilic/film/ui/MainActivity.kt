package com.bojanvilic.film.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bojanvilic.film.theme.AppTheme
import com.bojanvilic.film.ui.components.ConversationsList
import com.bojanvilic.film.ui.components.TabRow
import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.models.MessageType

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val allScreens = TopLevelDestinations.values().toList()
                val navController = rememberNavController()
                val backstackEntry = navController.currentBackStackEntryAsState()
                val currentScreen: TopLevelDestinations? = TopLevelDestinations.fromRoute(backstackEntry.value?.destination?.route)

                Scaffold(
                    topBar = {
                        if (currentScreen != null) {
                            TabRow(
                                allScreens = allScreens,
                                onTabSelected = { screen ->
                                    navController.navigate(screen.route)
                                },
                                currentScreen = currentScreen
                            )
                        }
                    }
                ) { paddingValues ->
                    ConversationsList(
                        paddingValues = paddingValues,
                        conversationsList = conversationList
                    )
                }
            }
        }
    }
}

val conversationList: List<Conversation> = listOf(
    Conversation(
        image = "",
        name = "Tanja Boskovic",
        previousMessageType = MessageType.VoiceMessage,
        timestamp = "13:45"
    ),
    Conversation(
        image = "",
        name = "Lidija",
        previousMessageType = MessageType.Image,
        timestamp = "14:45"
    ),
    Conversation(
        image = "",
        name = "Nino",
        previousMessageType = MessageType.Text,
        timestamp = "09:45"
    ),
    Conversation(
        image = "",
        name = "Marijana Nikolic",
        previousMessageType = MessageType.VoiceMessage,
        timestamp = "16:45"
    ),
    Conversation(
        image = "",
        name = "Bole",
        previousMessageType = MessageType.Image,
        timestamp = "21:35",
        hasActiveStory = true
    ),
    Conversation(
        image = "",
        name = "Mile",
        previousMessageType = MessageType.Text,
        timestamp = "22:25"
    )
)