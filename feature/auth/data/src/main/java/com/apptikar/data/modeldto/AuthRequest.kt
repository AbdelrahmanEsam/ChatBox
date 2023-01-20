package com.apptikar.data.modeldto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(@SerialName("username")val username : String,@SerialName("numberId")val numberId:String,@SerialName("password")val password : String)
