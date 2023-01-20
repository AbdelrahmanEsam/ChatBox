package com.apptikar.data.repository

import com.apptikar.data.remote.SearchUserService
import com.apptikar.feature.chat.domain.model.UserResponse
import com.apptikar.feature.chat.domain.repository.SearchRepository
import mapToUserResponse
import javax.inject.Inject

class SearchUserByNumberIdRepositoryImp @Inject constructor(private  val searchUserService: SearchUserService) : SearchRepository {

    override suspend fun searchUserByNumberId(numberId: String): List<UserResponse> {
        return searchUserService.searchUser(numberId).map { it.mapToUserResponse() }
    }
}