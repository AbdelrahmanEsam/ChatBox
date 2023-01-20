package com.apptikar.feature.chat.domain.usecases

import com.apptikar.feature.chat.domain.model.ChatState
import com.apptikar.feature.chat.domain.repository.GetChatsRepository

class GetChatsUseCase(private val getChatRepository: GetChatsRepository) {

    suspend operator fun invoke() : MutableSet<ChatState>
    {
        return  getChatRepository.getChats()
    }
}