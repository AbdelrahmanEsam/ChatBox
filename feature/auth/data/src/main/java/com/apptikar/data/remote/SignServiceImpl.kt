package com.apptikar.data.remote

import com.apptikar.data.modeldto.AuthRequest
import com.apptikar.data.modeldto.AuthResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class SignServiceImpl(private val client: HttpClient) : SignService {
    override suspend fun signUp(
        username: String,
        password: String,
        numberId: String
    ): HttpResponse {
       val res =  client.post(SignService.Endpoints.SignUp.url) {
            contentType(ContentType.Application.Json)
         setBody(AuthRequest(username =  username,password = password ,numberId = numberId))
        }
        return res
    }

    override suspend fun signIn(numberId: String, password: String): AuthResponse {
           return try{
             val response =   client.post(SignService.Endpoints.SignIn.url)
               {
                   contentType(ContentType.Application.Json)
                   setBody(AuthRequest(username =  "",password = password ,numberId = numberId))
               }
               return  response.body()
           }catch (exception : Exception)
           {
               AuthResponse("")
           }
    }
}