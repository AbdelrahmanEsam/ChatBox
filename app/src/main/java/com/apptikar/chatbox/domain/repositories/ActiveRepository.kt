package com.apptikar.chatbox.domain.repositories

import kotlinx.coroutines.flow.Flow

interface ActiveRepository {


    suspend fun readToken() :  Flow<String>

    suspend fun active() : Boolean

    suspend fun notActive() : Boolean

}