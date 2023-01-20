package com.apptikar.feature.chat.domain.usecases

import com.apptikar.feature.chat.domain.model.Message
import com.apptikar.feature.chat.domain.repository.MessageRepository
import javax.inject.Inject

class GetAllMessagesUseCase @Inject constructor(private val messageRepository: MessageRepository) {



    suspend operator fun invoke(): List<Message> {
        return messageRepository.getAllMessages()
    }
}