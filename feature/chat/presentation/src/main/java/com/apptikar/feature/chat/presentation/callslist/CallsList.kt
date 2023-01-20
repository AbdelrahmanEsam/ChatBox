package com.apptikar.feature.chat.presentation.callslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.apptikar.designsystem.Destinations
import com.apptikar.designsystem.theme.BlackText
import com.apptikar.designsystem.theme.GrayText
import com.apptikar.designsystem.theme.Green
import com.apptikar.designsystem.theme.White
import com.apptikar.feature.chat.presentation.R
import com.apptikar.feature.chat.presentation.components.ProfileImage
import com.apptikar.feature.chat.presentation.components.ProfileNameAndLastMessage


@Composable
fun ContactsList(modifier: Modifier, navController: NavController,contactsViewModel: ContactsViewModel = hiltViewModel()) {
    val contactsList = contactsViewModel.contactsList.collectAsStateWithLifecycle()
    val searchNumber = contactsViewModel.searchNumber.collectAsStateWithLifecycle()
    Column(modifier, verticalArrangement = Arrangement.Top) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Green)
            .padding(10.dp),) {
            TextField(
                value = searchNumber.value,
                onValueChange = { contactsViewModel.setSearchNumber(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(15.dp))
                    .background(White),
                textStyle = MaterialTheme.typography.h5.copy(color = BlackText, fontSize = 15.sp),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors =  TextFieldDefaults.textFieldColors(
                    backgroundColor = White,
                    focusedIndicatorColor = White,
                    unfocusedIndicatorColor = White
                ),
                placeholder = {
                    Text(text = "contact number", style = MaterialTheme.typography.h5.copy(color = GrayText, fontSize = 15.sp))
                }
            )
        }

        Spacer(modifier = Modifier.size(25.dp))
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(White)
            , contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),  verticalArrangement = Arrangement.spacedBy(10.dp)){

            if (contactsList.value.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.list_vector),
                            contentDescription = "icon"
                        )
                    }
                }
            }

            items(contactsList.value.size){ indexOfState ->
                        Row(modifier = Modifier.wrapContentSize().clickable {
                               navController.navigate(Destinations.Chat+ "/${contactsList.value[indexOfState].numberId}")
                        }, horizontalArrangement = Arrangement.Start,   verticalAlignment = Alignment.CenterVertically) {
                            ProfileImage(image = "", isOnline = true)
                            Spacer(modifier = Modifier.size(15.dp))
                            ProfileNameAndLastMessage(name = contactsList.value[indexOfState].userName, lastMessage = contactsList.value[indexOfState].numberId)
                        }
            }
        }
    }
}