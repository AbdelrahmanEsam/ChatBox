package com.apptikar.data.remote

import com.apptikar.data.dto.ChatStateDto
import com.apptikar.feature.chat.domain.model.ChatState

interface GetChatsService {

    suspend fun getChats(): MutableSet<ChatState>



    sealed class Endpoints(val url: String) {
        object GetChats: Endpoints("/getAllChats")
    }
}