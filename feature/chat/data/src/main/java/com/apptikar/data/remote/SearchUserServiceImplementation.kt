package com.apptikar.data.remote

import UserResponseDto
import android.util.Log
import com.apptikar.data.dto.SearchRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class SearchUserServiceImplementation(private  val client : HttpClient) : SearchUserService{
    override suspend fun searchUser(numberId: String): List<UserResponseDto> {
        return try {
         client.get(SearchUserService.Endpoints.SearchUser.url){
               contentType(ContentType.Application.Json)
               setBody(SearchRequest(numberId))
           }.body()
        } catch (e: Exception) {
            Log.d("searchResult",e.message.toString())
            mutableListOf()
        }
    }
}