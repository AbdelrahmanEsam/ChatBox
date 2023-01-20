package com.apptikar.data.remote

import com.apptikar.feature.chat.domain.model.Message


interface MessageService {

    suspend fun getAllMessages(receiverNumberId: String): List<Message>



    sealed class Endpoints(val url: String) {
        object GetAllMessages: Endpoints("/messages")
    }
}