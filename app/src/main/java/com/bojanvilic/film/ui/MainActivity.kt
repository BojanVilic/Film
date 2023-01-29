package com.bojanvilic.film.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.bojanvilic.film.ui.navigation.*
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val allScreens = TopLevelDestinations.values().toList()
                val navController = rememberNavController()
                val backstackEntry = navController.currentBackStackEntryAsState()
                val currentScreen: TopLevelDestinations? = TopLevelDestinations.fromRoute(backstackEntry.value?.destination?.route)

                if (backstackEntry.value?.destination?.route == "chat_route") {
                    chatViewModel.readMessage()
                }

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
                    AppNavHost(chatViewModel, navController, modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    chatViewModel: ChatViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TopLevelDestinations.All.route,
        modifier = modifier
    ) {
        allScreen(navController, chatViewModel)
        generalScreen(navController, chatViewModel)
        requestsScreen(navController, chatViewModel)
        chatScreen(onBackClicked = { navController.navigateUp() }, chatViewModel)
        storyScreen(onBackClicked = { navController.navigateUp() })
    }
}