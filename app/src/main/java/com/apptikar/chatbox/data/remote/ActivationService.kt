package com.apptikar.chatbox.data.remote

interface ActivationService {

    suspend fun active(token : String): Boolean

    suspend fun notActive(token : String): Boolean

    sealed class Endpoints(val url: String) {
        object Active : Endpoints("active")
        object NotActive : Endpoints("notActive")

    }
}