package com.apptikar.feature.chat.domain.model


data class ChatState(val numberId : String , val isOnline : Boolean = false,val imageUrl : String = "" ,val lastMessage : String
,val lastSeen : String,val unseenMessages : Int)