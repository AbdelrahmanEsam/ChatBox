package com.apptikar.chatbox.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.apptikar.designsystem.Destinations
import com.apptikar.feature.auth.presentation.navigation.authNavigation
import com.apptikar.feature.chat.presentation.navigation.homeNavigation

@Composable
fun ChatBoxNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    authenticated: Boolean,
) {



    NavHost(
        navController = navController,
        startDestination = if (authenticated) Destinations.Home else Destinations.OnBoarding,
        modifier = modifier
    ) {



//           AuthNavHost()
           authNavigation(navController)



//            HomeNavHost()
               homeNavigation(navController)

    }


}