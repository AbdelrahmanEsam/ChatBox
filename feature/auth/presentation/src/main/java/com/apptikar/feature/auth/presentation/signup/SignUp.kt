package com.apptikar.feature.auth.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.apptikar.designsystem.Destinations
import com.apptikar.designsystem.theme.BlackText
import com.apptikar.designsystem.theme.GrayText
import com.apptikar.feature.auth.presentation.R
import com.apptikar.feature.auth.presentation.components.InputField
import com.apptikar.feature.auth.presentation.components.RoundedCornerButton


@Composable
fun SignUp(modifier: Modifier, navController: NavController,viewModel: SignUpViewModel = hiltViewModel()) {

  val signUpState =   viewModel.successfulSignUp.collectAsState()
    val username = viewModel.username.collectAsState()
    val number = viewModel.numberId.collectAsState()
    val password = viewModel.password.collectAsState()
    val confirmPassword = viewModel.confirmPassword.collectAsState()

   val context =  LocalContext.current

LaunchedEffect(key1 = signUpState.value)
{
    if (signUpState.value == true)
    {
        Toast.makeText(context, "you have signedUp successfully", Toast.LENGTH_SHORT).show()
        navController.navigate(Destinations.Login)
    }else if (signUpState.value == false)
    {
        Toast.makeText(context, "something went wrong please try again", Toast.LENGTH_SHORT).show()
    }
}

    Column(modifier = modifier , horizontalAlignment = Alignment.CenterHorizontally)
    {

        Spacer(modifier = Modifier.size(50.dp))

        Image(modifier = Modifier.align(Alignment.Start),painter = painterResource(id = R.drawable.back), contentDescription = "back" )

        Spacer(modifier = Modifier.size(50.dp))
        Text(text = stringResource(id = R.string.signup_into), style = MaterialTheme.typography.h4.copy(color = BlackText, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        Text(text = stringResource(id = R.string.signup_brief), style = MaterialTheme.typography.h5.copy(color = GrayText, fontSize = 15.sp), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(50.dp))
        InputField(R.string.your_name, value = username.value)
        {
            viewModel.onUsernameChange(it)
        }
        Spacer(modifier = Modifier.size(40.dp))
        InputField(R.string.your_number,value = number.value)
        {
            viewModel.onNumberChangeChange(it)
        }
        Spacer(modifier = Modifier.size(40.dp))
        InputField(R.string.password,value = password.value,inputType = KeyboardType.Password)
        {
            viewModel.onPasswordChange(it)
        }
        Spacer(modifier = Modifier.size(40.dp))
        InputField(R.string.confirm_password, value = confirmPassword.value,inputType = KeyboardType.Password)
        {
            viewModel.onConfirmPasswordChange(it)
        }
        Spacer(modifier = Modifier.size(100.dp))
        RoundedCornerButton(
            text = stringResource(id = R.string.create_account),
            true,

        ){
            viewModel.signUp()
        }
        Spacer(modifier = Modifier.size(15.dp))


    }

}


