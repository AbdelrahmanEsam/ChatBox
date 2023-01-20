package com.apptikar.feature.chat.presentation.state

import com.apptikar.designsystem.Destinations
import com.apptikar.feature.chat.presentation.R

sealed class BottomScreen(val route : String , val title:Int , val icon :Int)
{
    object Home : BottomScreen(route = Destinations.Home,title= R.string.messages,R.drawable.message)

    object Calls : BottomScreen(route = Destinations.Calls,title= R.string.calls,R.drawable.call)

    object Contacts : BottomScreen(route = Destinations.Contacts,title= R.string.contacts,R.drawable.contact)
}
