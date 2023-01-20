package com.apptikar.data.dto

import com.apptikar.feature.chat.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*

@Serializable
data class MessageDto(
    val text: String,
    val timestamp: Long,
    val username: String,
    val id: String
) {
    fun toMessage(): Message {
        val date = Date(timestamp)
        val formattedDate = DateFormat
            .getDateInstance(DateFormat.DATE_FIELD)
            .format(date)
        return Message(
            text = text,
            formattedTime = formattedDate,
            username = username
        )
    }
}
