package com.apptikar.data.remote

import com.apptikar.core.common.Resource
import com.apptikar.data.dto.MessageDto
import com.apptikar.data.dto.SendMessageDto
import com.apptikar.feature.chat.domain.model.Message
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChatSocketServiceImpl(
    private val client: HttpClient
): ChatSocketService {

    private var socket: WebSocketSession? = null

    override suspend fun initSession(): Resource<Unit> {
        return try {
            socket = client.webSocketSession {
                url(ChatSocketService.Endpoints.ChatSocket.url)
            }
            if(socket?.isActive == true) {
                Resource.Success(Unit)
            } else Resource.Error("Couldn't establish a connection.")
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun sendMessage(message: String,receiver : String) {
        try {
            val sentMessage = SendMessageDto(message = message,receiverId = receiver)
            socket?.send(Frame.Text(Json.encodeToString(sentMessage)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<Message> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText() ?: ""
                    val messageDto = Json.decodeFromString<MessageDto>(json)
                    messageDto.toMessage()
                } ?: flow { }
        } catch(e: Exception) {
            e.printStackTrace()
            flow {  }
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }
}