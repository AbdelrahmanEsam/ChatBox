package com.apptikar.domain.repositories
import kotlinx.coroutines.flow.Flow


interface DataStoreOperations {

    suspend fun saveSignInState(token : String)

    fun  readSignInState() : Flow<String>


}