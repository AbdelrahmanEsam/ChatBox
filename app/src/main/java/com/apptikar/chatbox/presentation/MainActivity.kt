package com.apptikar.chatbox.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


  private val mainViewModel : MainViewModel by viewModels()

   private var authenticated : Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition{authenticated == null}
        setContent {
             val successful = mainViewModel.successful.collectAsStateWithLifecycle()
             authenticated = successful.value
            successful.value?.let { ChatBoxModal(it) }
        }


    }

    override fun onStart() {
        super.onStart()
        mainViewModel.active()
    }



    override fun onStop() {
        super.onStop()
        mainViewModel.unActive()
    }
}