package com.apptikar.data.remote

import UserResponseDto

interface SearchUserService {

    suspend fun searchUser(numberId : String) : List<UserResponseDto>




    sealed class Endpoints(val url: String) {
        object SearchUser: Endpoints("/searchUser")
    }
}