package com.apptikar.feature.chat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.apptikar.designsystem.Destinations


//@Composable
//fun HomeNavHost( navController:NavHostController,username : String) {
//    NavHost(
//        navController = navController,
//        startDestination = Destinations.Chat,
//    ) {
//        homeNavigation(username,navController)
//    }
//}

@Composable
fun NavGraphBuilder.HomeNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Chat,

    ) {


        homeNavigation(navController)
    }
}