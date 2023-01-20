package com.apptikar.data.repositories

import android.util.Log
import com.apptikar.data.modeldto.AuthResponse
import com.apptikar.data.remote.SignService
import com.apptikar.domain.repositories.SignRepository
import com.apptikar.domain.repositories.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignRepositoryImp @Inject constructor(private val dataStoreOperations : DataStoreOperations, private val authenticationService: SignService)
    : SignRepository {
    override suspend fun signIn(numberId: String, password: String) : Boolean {
     val authResponse : AuthResponse =  authenticationService.signIn(numberId,password)
     if (authResponse.token.isNotEmpty()) {dataStoreOperations.saveSignInState(authResponse.token);return true}
        
        return false
    }

    override suspend fun signUp(numberId: String, password: String, username: String) : Boolean {
          val response =  authenticationService.signUp(username,password,numberId)
       return response.status.value == 200
    }

    override suspend fun readToken(): Flow<String> {
       return dataStoreOperations.readSignInState()
    }

}