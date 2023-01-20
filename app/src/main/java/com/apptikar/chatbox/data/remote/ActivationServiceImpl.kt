package com.apptikar.chatbox.data.remote

import android.util.Log
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

class ActivationServiceImpl(private val client: HttpClient) : ActivationService {

    override suspend fun active(token : String): Boolean {
        return try {
       client.get(ActivationService.Endpoints.Active.url){
           timeout {requestTimeoutMillis =  5000 }
              headers{
                  append(HttpHeaders.Authorization, "Bearer $token")
              }
            }.status.value == 200
        }catch (exception : Exception)
        {
            false
        }
    }

    override suspend fun notActive(token: String): Boolean {
        return try {
            client.get(ActivationService.Endpoints.NotActive.url){
                timeout {requestTimeoutMillis =  5000 }
                headers{
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }.status.value == 200
        }catch (exception : Exception)
        {
            false
        }


    }
}