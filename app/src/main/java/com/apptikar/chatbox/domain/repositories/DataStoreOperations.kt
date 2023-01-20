package com.apptikar.chatbox.domain.repositories
import kotlinx.coroutines.flow.Flow


interface DataStoreAuthentication {


    fun  readSignInState() : Flow<String>


}