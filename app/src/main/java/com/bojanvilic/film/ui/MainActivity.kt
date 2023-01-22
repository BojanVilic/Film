package com.bojanvilic.film.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bojanvilic.film.theme.AppTheme
import com.bojanvilic.film.ui.components.TabRow
import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.models.MessageType
import com.bojanvilic.film.ui.navigation.allScreen
import com.bojanvilic.film.ui.navigation.chatScreen
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
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
                    AppNavHost(navController, modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TopLevelDestinations.All.route,
        modifier = modifier
    ) {
        allScreen(navController)
        chatScreen(onBackClicked = { navController.navigateUp() })
    }
}

val conversationList: List<Conversation> = listOf(
    Conversation(
        id = 0,
        image = "",
        name = "Tanja Boskovic",
        previousMessageType = MessageType.VoiceMessage,
        timestamp = "13:45",
        hasUnreadMessage = true,
        isActive = true
    ),
    Conversation(
        id = 1,
        image = "",
        name = "Lidija",
        previousMessageType = MessageType.Image,
        timestamp = "14:45",
        previousMessageText = "Eo me."
    ),
    Conversation(
        id = 2,
        image = "",
        name = "Nino",
        previousMessageType = MessageType.Text,
        timestamp = "09:45",
        previousMessageText = "Kako je u Londonetu?"
    ),
    Conversation(
        id = 3,
        image = "",
        name = "Marijana Nikolic",
        previousMessageType = MessageType.VoiceMessage,
        timestamp = "16:45",
        hasUnreadMessage = true,
        isActive = true
    ),
    Conversation(
        id = 4,
        image = "",
        name = "Bole",
        previousMessageType = MessageType.Image,
        timestamp = "21:35",
        hasActiveStory = true,
        isActive = true
    ),
    Conversation(
        id = 5,
        image = "",
        name = "Mile",
        previousMessageType = MessageType.Text,
        timestamp = "22:25",
        previousMessageText = "Poslao sam ti poruku."
    ),
    Conversation(
        id = 6,
        image = "",
        name = "Bole",
        previousMessageType = MessageType.Image,
        timestamp = "21:35",
        hasActiveStory = true,
        isActive = true
    ),
    Conversation(
        id = 7,
        image = "",
        name = "Mile",
        previousMessageType = MessageType.Text,
        timestamp = "22:25",
        previousMessageText = "Poslao sam ti poruku."
    ),
    Conversation(
        id = 8,
        image = "",
        name = "Bole",
        previousMessageType = MessageType.Image,
        timestamp = "21:35",
        hasActiveStory = true,
        isActive = true
    ),
    Conversation(
        id = 9,
        image = "",
        name = "Mile",
        previousMessageType = MessageType.Text,
        timestamp = "22:25",
        previousMessageText = "Poslao sam ti poruku."
    )
)