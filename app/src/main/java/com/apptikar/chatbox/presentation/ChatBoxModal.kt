package com.apptikar.chatbox.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.apptikar.designsystem.theme.ChatBoxTheme

@Composable
fun ChatBoxModal(authenticated: Boolean) {

    ChatBoxTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                scaffoldState = scaffoldState,

            ){paddingValues ->

                Row(Modifier.fillMaxSize()) {
                    ChatBoxNavGraph(
                        navController = navController ,
                        modifier = Modifier.padding(paddingValues),
                        authenticated
                    )

                }

            }
        }
    }

}