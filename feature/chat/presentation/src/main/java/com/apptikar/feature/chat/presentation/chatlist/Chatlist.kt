package com.apptikar.feature.chat.presentation.chatlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.apptikar.designsystem.Destinations
import com.apptikar.designsystem.theme.DismissWhite
import com.apptikar.designsystem.theme.RedDelete
import com.apptikar.designsystem.theme.White
import com.apptikar.feature.chat.presentation.R
import com.apptikar.feature.chat.presentation.components.ChatItem
import com.apptikar.feature.chat.presentation.components.HomeHeader
import com.apptikar.feature.chat.presentation.components.IconWithCircleBack
import com.apptikar.feature.chat.presentation.state.Swipe
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatList(modifier: Modifier, navController: NavController,viewModel: ChatListViewModel = hiltViewModel()) {

    val chatsList = viewModel.recentChats.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.getChats()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(modifier, verticalArrangement = Arrangement.Top) {
        Spacer(modifier = Modifier.size(25.dp))
        HomeHeader(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 10.dp))
        {
            navigateToContacts(navController)
        }
        
        Spacer(modifier = Modifier.size(25.dp))
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(White)
            , contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),  verticalArrangement = Arrangement.spacedBy(10.dp)){

            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp), horizontalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(id = R.drawable.list_vector), contentDescription = "icon")
                }
            }

            items(chatsList.value.size){ indexOfState ->

                val swipeableState = rememberSwipeableState(initialValue = Swipe.EXPANDED)


                Box(modifier = Modifier) {
                    ButtonsBehind(if (swipeableState.currentValue ==Swipe.EXPANDED)  White else DismissWhite)
                    Box(modifier = Modifier
                        .wrapContentSize()
                        .swipeable(
                            state = swipeableState,
                            orientation = Orientation.Horizontal,
                            anchors = mapOf(
                                0f to Swipe.EXPANDED,
                                with(LocalDensity.current) { -150.dp.toPx() } to Swipe.COLLAPSED,
                            )
                        )
                        .offset {
                            IntOffset(
                                y = 0,
                                x = swipeableState.offset.value.roundToInt()
                            )
                        }.clickable {
                            navController.navigate(Destinations.Chat)
                        }) {

                        ChatItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(if (swipeableState.currentValue ==Swipe.EXPANDED)  White else DismissWhite).clickable {
                                    navController.navigate(Destinations.Chat+ "/${chatsList.value.elementAt(indexOfState).numberId}")
                                },
                            chatState = chatsList.value.elementAt(indexOfState),
                        )


                    }

                }


            }


        }
    }
}


@Composable
fun ButtonsBehind(background : Color) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(15.dp))
        .background(background)
        .padding(horizontal = 10.dp, vertical = 10.dp), horizontalArrangement = Arrangement.End) {


        IconWithCircleBack(
            modifier = Modifier.size(50.dp),
            background = Color.Black,
            icon = R.drawable.notification,
            contentDescription = R.string.notification_button
        )
        Spacer(modifier = Modifier.size(15.dp))
        IconWithCircleBack(
            modifier = Modifier.size(50.dp),
            background = RedDelete,
            icon = R.drawable.trash,
            contentDescription = R.string.trash_button
        )
    }
}

fun navigateToContacts(navController: NavController)
{
    navController.navigate(Destinations.Contacts)
}