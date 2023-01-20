package com.apptikar.data.remote

import com.apptikar.data.dto.GetAllMessageDto
import com.apptikar.data.dto.MessageDto
import com.apptikar.data.dto.SearchRequest
import com.apptikar.feature.chat.domain.model.Message
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class MessageServiceImpl(
    private val client: HttpClient
): MessageService {

    override suspend fun getAllMessages(receiverNumberId: String): List<Message> {
        return try {
            client.get(MessageService.Endpoints.GetAllMessages.url){
                contentType(ContentType.Application.Json)
                setBody(GetAllMessageDto(receiverNumberId))
            }.body<List<MessageDto>>()
                .map { it.toMessage() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}