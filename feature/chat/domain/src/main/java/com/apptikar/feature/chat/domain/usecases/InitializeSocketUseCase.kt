package com.apptikar.feature.chat.domain.usecases

import com.apptikar.core.common.Resource
import com.apptikar.feature.chat.domain.repository.MessageRepository
import javax.inject.Inject

class InitializeSocketUseCase @Inject constructor(private val messageRepository: MessageRepository) {

    suspend operator fun invoke() : Resource<Unit>
    {
      return  messageRepository.initWebSocket()
    }
}