package com.apptikar.data.remote


import com.apptikar.core.common.Resource
import com.apptikar.feature.chat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
    ): Resource<Unit>

    suspend fun sendMessage(message: String,receiver : String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://192.168.1.7:8099"
    }

    sealed class Endpoints(val url: String) {
        object ChatSocket: Endpoints("$BASE_URL/chat-socket")
    }
}