package com.apptikar.feature.chat.domain.usecases

import com.apptikar.feature.chat.domain.model.UserResponse
import com.apptikar.feature.chat.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUserByNumberIdUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(numberId : String) : List<UserResponse>
    {
          return searchRepository.searchUserByNumberId(numberId)
    }
}