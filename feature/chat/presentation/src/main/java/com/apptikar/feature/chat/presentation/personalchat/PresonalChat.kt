package com.apptikar.feature.chat.presentation.personalchat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apptikar.designsystem.theme.GrayText
import com.apptikar.feature.chat.presentation.components.IncomeMessage
import com.apptikar.feature.chat.presentation.components.OutcomeMessage
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ChatScreen(
    viewModel: PersonalChatViewModel = hiltViewModel(),
    receiverNumberId: String?
) {
    val context = LocalContext.current
    val textMessage = viewModel.messageText.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        viewModel.toastEvent.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.connectToChat(receiverNumberId!!)
            } else if (event == Lifecycle.Event.ON_STOP) {
                viewModel.disconnect()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val state = viewModel.state.collectAsState()
    val visibilityState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            state = visibilityState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(state.value.messages) { message ->
                val isOwnMessage = message.username != receiverNumberId
                Column(
                    horizontalAlignment = if (isOwnMessage) {
                         Alignment.End
                    } else Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isOwnMessage)
                    {

                        OutcomeMessage(modifier = Modifier.wrapContentSize()) {
                            Text(text = message.text, style = TextStyle(color = Color.White))
                        }
                        Text(text = message.formattedTime, style = TextStyle(color = GrayText))
                    }else
                    {
                        IncomeMessage(modifier = Modifier.wrapContentSize()) {
                        Text(text = message.text, style = TextStyle(color = Color.White))
                        }
                        Text(text = message.text, style = TextStyle(color = GrayText))
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = textMessage.value,
                onValueChange = viewModel::onMessageChange,
                placeholder = {
                    Text(text = "Enter a message")
                },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                viewModel.sendMessage(receiverNumberId!!)
            }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send"
                )
            }
        }
    }
}
