package com.apptikar.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageDto(val receiverId : String , val message : String )