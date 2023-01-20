package com.apptikar.feature.chat.domain.repository

import com.apptikar.feature.chat.domain.model.UserResponse

interface SearchRepository {

    suspend  fun searchUserByNumberId(numberId : String) : List<UserResponse>
}