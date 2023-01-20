package com.apptikar.feature.chat.domain.repository

import com.apptikar.feature.chat.domain.model.ChatState

interface GetChatsRepository  {

  suspend fun getChats() : MutableSet<ChatState>
}