package com.apptikar.data.repository

import com.apptikar.data.remote.GetChatsService
import com.apptikar.feature.chat.domain.model.ChatState
import com.apptikar.feature.chat.domain.repository.GetChatsRepository
import io.ktor.client.*

class GetChatsRepositoryImpl(private val getChatsService: GetChatsService) : GetChatsRepository{

    override suspend fun getChats(): MutableSet<ChatState> {
        return getChatsService.getChats()
    }
}