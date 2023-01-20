package com.apptikar.feature.chat.presentation.state

import com.apptikar.feature.chat.domain.model.Message

data class ChatState(val messages : List<Message> = emptyList(), val isLoading : Boolean = false )
