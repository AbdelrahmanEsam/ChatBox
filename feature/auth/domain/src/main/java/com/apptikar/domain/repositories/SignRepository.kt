package com.apptikar.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SignRepository {

    suspend fun signIn(numberId:String,password : String) : Boolean

    suspend fun signUp(numberId: String,password: String,username:String) : Boolean

    suspend fun readToken() :  Flow<String>


}