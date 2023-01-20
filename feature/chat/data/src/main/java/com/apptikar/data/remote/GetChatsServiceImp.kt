package com.apptikar.data.remote

import com.apptikar.data.dto.ChatStateDto
import com.apptikar.data.dto.toChatState
import com.apptikar.feature.chat.domain.model.ChatState
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class GetChatsServiceImp(private val client: HttpClient) : GetChatsService {
    override suspend fun getChats(): MutableSet<ChatState> {
       return try {
           client.get(GetChatsService.Endpoints.GetChats.url)
               .body<MutableSet<ChatStateDto>>().map { it.toChatState() }.toMutableSet()
       } catch(e : Exception)
       {
           mutableSetOf()
       }
    }
}