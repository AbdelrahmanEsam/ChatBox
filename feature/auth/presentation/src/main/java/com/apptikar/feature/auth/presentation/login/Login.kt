package com.apptikar.feature.auth.presentation.login

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.apptikar.Constants.Google_Client_Id
import com.apptikar.designsystem.Destinations
import com.apptikar.designsystem.theme.*
import com.apptikar.feature.auth.presentation.R
import com.apptikar.feature.auth.presentation.components.ButtonsSection
import com.apptikar.feature.auth.presentation.components.InputField
import com.apptikar.feature.auth.presentation.components.OrDividerRow
import com.apptikar.feature.auth.presentation.components.RoundedCornerButton
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.tasks.await


@Composable
fun Login(
    modifier: Modifier, navController: NavController,
    viewModel : LoginViewModel = hiltViewModel(),
) {
    val activity  = LocalContext.current as Activity
    val emailIsError = rememberSaveable { mutableStateOf(false) }
    val passIsError = rememberSaveable { mutableStateOf(false) }
    val canLogin = rememberSaveable{mutableStateOf(false)}
    val numberId = viewModel.numberId.collectAsState()
    val password = viewModel.password.collectAsState()
//    val signInGoogleState = viewModel.signInGoogleState.collectAsState()
//    val signInFacebookState = viewModel.signInFacebookState.collectAsState()
    val rememberLauncher = rememberLauncherForActivityResult(
        contract = LoginManager.getInstance().createLogInActivityResultContract()
    ){
        LoginManager.getInstance().onActivityResult(it.resultCode, it.data, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
            }
            override fun onCancel() {
                Toast.makeText(activity, "cancer", Toast.LENGTH_SHORT).show()
            }
            override fun onError(error: FacebookException) {
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }




    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.successful.collectLatest { successful ->

            if (successful) {
                val deepLinkRequest = NavDeepLinkRequest.Builder
                    .fromUri("chatbox://home".toUri()).build()

                navController.navigate(deepLinkRequest)
            }
        }
    }




     Column(modifier = modifier , horizontalAlignment = CenterHorizontally)
    {

        Spacer(modifier = Modifier.size(50.dp))
        Image(modifier = Modifier.align(Alignment.Start),painter = painterResource(id = R.drawable.back), contentDescription = "back" )
        Spacer(modifier = Modifier.size(50.dp))
        Text(text = stringResource(id = R.string.login_into), style = MaterialTheme.typography.h4.copy(color = BlackText, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        Text(text = stringResource(id = R.string.login_brief), style = MaterialTheme.typography.h5.copy(color = GrayText, fontSize = 15.sp), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(40.dp))
        ButtonsSection(googleIcon = R.drawable.google_login, facebookIcon = R.drawable.facebook_login, appleIcon =R.drawable.apple_login
//            , googleAction = {viewModel.setSignInGoogleState(true)},
//            facebookAction = { viewModel.setSignInFacebookState(true) }
            )
        Spacer(modifier = Modifier.size(40.dp))
        OrDividerRow(dividerColor = GrayDivider, textColor = GrayOr)
        Spacer(modifier = Modifier.size(40.dp))
        InputField(R.string.your_number, emailIsError.value,R.string.error,value = numberId.value){ newNumberValue ->
            viewModel.onNumberChangeChange(newNumberValue)
        }
        Spacer(modifier = Modifier.size(30.dp))
            InputField(R.string.password, isError = passIsError.value,value = password.value, inputType = KeyboardType.Password, action = { newPasswordValue ->
                viewModel.onPasswordChange(newPasswordValue)

            })
        Spacer(modifier = Modifier.size(150.dp))
        RoundedCornerButton(
            text = stringResource(id = R.string.login),
            canLogin.value
        ) {
            viewModel.signIn()
        }
        Spacer(modifier = Modifier.size(15.dp))
        ClickableText(text = AnnotatedString(stringResource(id = R.string.go_to_signup)), style = MaterialTheme.typography.h5.copy(color = TextGreen, fontSize = 15.sp), onClick = {
            navController.navigate(Destinations.SignUp)
        })


        Spacer(modifier = Modifier.size(15.dp))
    }




//    startActivityForFacebookResult(
//        key = signInFacebookState.value,
//        onResultReceived = { tokenId ->
//
//
//            Toast.makeText(activity, tokenId.token, Toast.LENGTH_LONG).show()
//            viewModel.setSignInValue(true)
//        },
//        onDialogDismiss = {
//            viewModel.setSignInFacebookState(false)
//
//        },
//    ){  activityLauncher,context ->
//
//
//        if (signInFacebookState.value) {
//            activityLauncher.launch(emptyList())
//        }
//    }
//
//
//
//    startActivityForGoogleResult(
//        key = signInGoogleState.value,
//        onResultReceived = { tokenId ->
//
//
//            Toast.makeText(activity, tokenId, Toast.LENGTH_LONG).show()
//            viewModel.setSignInValue(true)
//
//        },
//        onDialogDismiss = {
//         viewModel.setSignInGoogleState(false)
//        },
//    ){ activityLauncher ->
//        if (signInGoogleState.value){
//            coroutineScope.launch {
//                signInWithGoogle( context = activity, launcher = { intentSenderRequest ->
//                    activityLauncher.launch(intentSenderRequest)
//                }, acceptNotFound = {
//                    viewModel.setSignInGoogleState(false)
//                })
//            }
//        }
//    }


}

@Composable
fun StartActivityForFacebookResult(
    key : Any,
    onResultReceived : (AccessToken) -> Unit,
    onDialogDismiss : () -> Unit,
    launcher: (ManagedActivityResultLauncher<Collection<String>, CallbackManager.ActivityResultParameters>,context:Context) -> Unit
) {

    val context = LocalContext.current
    val activityLauncher = rememberLauncherForActivityResult(contract = LoginManager.getInstance().createLogInActivityResultContract()){ result ->
        LoginManager.getInstance().onActivityResult(result.resultCode, result.data, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
               onResultReceived(result.accessToken)
            }
            override fun onCancel() {
               onDialogDismiss()
            }
            override fun onError(error: FacebookException) {
               onDialogDismiss()
            }
        })

    }

    LaunchedEffect(key1 = key){
        launcher(activityLauncher,context)
    }

}

@Composable
fun StartActivityForGoogleResult(
    key : Any,
   onResultReceived : (String) -> Unit,
    onDialogDismiss : () -> Unit,
    launcher: (ManagedActivityResultLauncher<IntentSenderRequest,ActivityResult>) -> Unit
) {
    val activity = LocalContext.current as Activity
    val activityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()){ result ->
        try {
            if (result.resultCode == Activity.RESULT_OK){
                val onTapClient = Identity.getSignInClient(activity)
                val credentials = onTapClient.getSignInCredentialFromIntent(result.data)
                val tokenId = credentials.googleIdToken

                if (tokenId != null)
                {
                    onResultReceived(tokenId)
                }
            }else{
                onDialogDismiss()
            }


        }catch (e:java.lang.Exception){
            Log.d("LOG", e.message.toString())
            onDialogDismiss()
        }
    }

    LaunchedEffect(key1 = key){
        launcher(activityLauncher)
    }

}







suspend fun signInWithGoogle(
    context: Context,
    launcher:  (IntentSenderRequest) -> Unit,
    acceptNotFound : () -> Unit
) {
    val oneTapClient = Identity.getSignInClient(context)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(Google_Client_Id)
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        // Automatically sign in when exactly one credential is retrieved.
        .setAutoSelectEnabled(true)
        .build()

    try {
        // Use await() from https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-play-services
        // Instead of listeners that aren't cleaned up automatically
        val result = oneTapClient.beginSignIn(signInRequest).await()

        // Now construct the IntentSenderRequest the launcher requires
        val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent).build()
        launcher(intentSenderRequest)
    } catch (e: Exception) {
        // No saved credentials found. Launch the One Tap sign-up flow, or
        // do nothing and continue presenting the signed-out UI.
        Log.d("LOG", e.message.toString() +"sign catch")
        signUpWithGoogle(context,launcher,acceptNotFound)
    }
}

private suspend fun signUpWithGoogle(
    context: Context,
    launcher:  (IntentSenderRequest) -> Unit,
     accountNotFound : () -> Unit){

    val oneTapClient = Identity.getSignInClient(context)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(Google_Client_Id)
                .build()
        ).build()

    try {
        // Use await() from https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-play-services
        // Instead of listeners that aren't cleaned up automatically
        val result = oneTapClient.beginSignIn(signInRequest).await()

        // Now construct the IntentSenderRequest the launcher requires
        val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent).build()
        launcher(intentSenderRequest)
    } catch (e: Exception) {
        // No saved credentials found. Launch the One Tap sign-up flow, or
        // do nothing and continue presenting the signed-out UI.
       accountNotFound()
    }


}








