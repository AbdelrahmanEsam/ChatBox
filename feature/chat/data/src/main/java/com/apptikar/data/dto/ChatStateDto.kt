package com.apptikar.data.dto

import com.apptikar.feature.chat.domain.model.ChatState
import kotlinx.serialization.Serializable

@Serializable
data class ChatStateDto( val numberId:String
, val isOnline : Boolean = false,val imageUrl : String = "" ,val lastMessage : String,val lastSeen : String
,val unseenMessages : Int)

fun ChatStateDto.toChatState() : ChatState
{
    return ChatState(numberId, isOnline, imageUrl, lastMessage, lastSeen, unseenMessages)
}
