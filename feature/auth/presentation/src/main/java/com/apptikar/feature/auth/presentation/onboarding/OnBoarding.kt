package com.apptikar.feature.auth.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.apptikar.designsystem.Destinations
import com.apptikar.designsystem.theme.*
import com.apptikar.feature.auth.presentation.R
import com.apptikar.feature.auth.presentation.components.LoginInText
import com.apptikar.feature.auth.presentation.components.OrDividerRow
import com.apptikar.feature.auth.presentation.components.ButtonsSection
import com.apptikar.feature.auth.presentation.components.RoundedCornerButton

@Composable
fun OnBoarding(modifier: Modifier, navController: NavController) {
    Column(modifier = modifier) {
            Spacer(modifier = Modifier.size(30.dp))
             Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                 Image(modifier = Modifier
                     .width(90.dp)
                     .height(25.dp),painter = painterResource(id = R.drawable.white_logo), contentDescription = "whiteLogo" )
             }
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = stringResource(id = R.string.onBoarding),
                fontFamily = FontFamily.Default,
                color = Color.White,
                fontSize = 70.sp
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = stringResource(id = R.string.brief),
                style = MaterialTheme.typography.h5.copy(color = GrayText),
            )
            Spacer(modifier = Modifier.size(35.dp))
           ButtonsSection(googleIcon = R.drawable.google_signup, facebookIcon =  R.drawable.facebook_signup, appleIcon = R.drawable.apple_signup)
            Spacer(modifier = Modifier.size(40.dp))
            OrDividerRow(dividerColor = GrayDivider,textColor = ORWhite)
            Spacer(modifier = Modifier.size(35.dp))
        RoundedCornerButton(text = stringResource(id = R.string.signup_with_email))
        {
            navigateToSignUp(navController)
        }
        Spacer(modifier = Modifier.size(35.dp))
        LoginInText { navigateToLogin(navController) }
        Spacer(modifier = Modifier.size(20.dp))


            


    }
}

private fun navigateToSignUp(navController: NavController)
{
    navController.navigate(Destinations.SignUp)

}

private fun navigateToLogin(navController: NavController)
{
    navController.navigate(Destinations.Login)
}



