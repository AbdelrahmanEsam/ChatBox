package com.apptikar.data.modeldto

import kotlinx.serialization.Serializable


@Serializable
data class AuthResponse(val token : String)