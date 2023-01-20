package com.apptikar.feature.chat.domain.model

data class RecentChats(val isOnline:Boolean = false, val image:String? = null, val name:String
                       , val lastMessage:String, val lastMessageTime:String, val numberOfUnseenMessages:Int)
