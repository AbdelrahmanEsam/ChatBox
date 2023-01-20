package com.apptikar.feature.chat.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.apptikar.designsystem.Destinations
import com.apptikar.designsystem.theme.White
import com.apptikar.feature.chat.presentation.callslist.ContactsList
import com.apptikar.feature.chat.presentation.chatlist.ChatList
import com.apptikar.feature.chat.presentation.personalchat.ChatScreen


fun NavGraphBuilder.homeNavigation(
    navController: NavController
) {



    composable(  route = Destinations.Home,
        deepLinks = listOf( navDeepLink { uriPattern = "chatbox://home" })
        ) {
         ChatList(  modifier = Modifier
             .fillMaxSize()
             .background(Color.Black)
             ,navController)
    }

    composable(  route = Destinations.Chat+"/{receiverNumberId}",arguments = listOf(
        navArgument("receiverNumberId"){
            defaultValue = ""
            type = NavType.StringType
        }
    ),) { navBackStackEntry ->

       val receiverNumberId =  navBackStackEntry.arguments?.getString("receiverNumberId")

        ChatScreen(receiverNumberId = receiverNumberId)
    }

    composable(  route = Destinations.Calls) {
         ContactsList(  modifier = Modifier
             .fillMaxSize()
             .background(White)
             .padding(horizontal = 30.dp)
             .verticalScroll(
                 rememberScrollState()
             ),navController)
    }

    composable(  route = Destinations.Contacts) {
        ContactsList(
            modifier =  Modifier
            .fillMaxSize()
            .background(White), navController = navController)
    }
}