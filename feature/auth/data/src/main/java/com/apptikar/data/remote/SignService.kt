package com.apptikar.data.remote

import com.apptikar.data.modeldto.AuthResponse
import io.ktor.client.statement.*

interface SignService {

    suspend fun signUp(username : String, password:String,numberId : String) : HttpResponse

    suspend fun signIn(numberId: String,password: String) : AuthResponse





    sealed class Endpoints(val url: String) {
        object SignUp: Endpoints("/signUp")
        object SignIn: Endpoints("/signIn")
    }
}