package com.apptikar.feature.auth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.apptikar.designsystem.Destinations

@Composable
fun NavGraphBuilder.AuthNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Login,
    ) {

        authNavigation(navController)
    }
}