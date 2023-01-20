package com.apptikar.chatbox.data.repositories

import com.apptikar.chatbox.data.remote.ActivationService
import com.apptikar.chatbox.domain.repositories.ActiveRepository
import com.apptikar.chatbox.domain.repositories.DataStoreAuthentication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ActiveRepositoryImp @Inject constructor(private val dataStoreOperations : DataStoreAuthentication, private val activationService: ActivationService)
    : ActiveRepository {

    override suspend fun readToken(): Flow<String> {
       return dataStoreOperations.readSignInState()
    }

    override suspend fun active(): Boolean {
       return activationService.active(readToken().first())
    }

    override suspend fun notActive(): Boolean {
         return activationService.notActive(readToken().first())
    }
}