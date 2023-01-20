package com.apptikar.feature.auth.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.apptikar.designsystem.Destinations
import com.apptikar.designsystem.theme.Purple
import com.apptikar.designsystem.theme.White
import com.apptikar.feature.auth.presentation.login.Login
import com.apptikar.feature.auth.presentation.onboarding.OnBoarding
import com.apptikar.feature.auth.presentation.signup.SignUp
import com.facebook.CallbackManager


fun NavGraphBuilder.authNavigation(
    navController: NavController,

) {



    composable(  route = Destinations.Login) {
        Login(
            modifier = Modifier
                .fillMaxSize().background(White)
                 .padding(horizontal = 30.dp)
                .verticalScroll(
                    rememberScrollState()
                ),navController)
    }




    composable(  route = Destinations.SignUp) {
        SignUp(
            modifier = Modifier
                .fillMaxSize().background(White)
                .padding(horizontal = 30.dp)
                .verticalScroll(
                    rememberScrollState()
                ),navController
        )
    }

    composable(  route = Destinations.OnBoarding) {
        OnBoarding(
            modifier = Modifier
                .fillMaxSize().background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Purple,
                            Color.Black
                        )
                    )).padding(horizontal = 10.dp)
                .verticalScroll(
                    rememberScrollState()
                ),navController
        )
    }
}