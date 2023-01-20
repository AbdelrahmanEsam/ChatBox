package com.apptikar.feature.chat.domain.repository

import com.apptikar.core.common.Resource
import com.apptikar.feature.chat.domain.model.Message

interface MessageRepository {

    suspend  fun initWebSocket() : Resource<Unit>

   suspend fun getAllMessages() : List<Message>

}