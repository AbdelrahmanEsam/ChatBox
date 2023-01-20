package com.apptikar.feature.chat.presentation.personalchat

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.core.common.Resource
import com.apptikar.data.remote.ChatSocketService
import com.apptikar.data.remote.MessageService
import com.apptikar.feature.chat.presentation.state.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonalChatViewModel @Inject constructor(
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _messageText = MutableStateFlow("")
    val messageText: StateFlow<String> = _messageText.asStateFlow()

    private val _state = MutableStateFlow(ChatState())
    val state: StateFlow<ChatState> = _state.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun connectToChat(receiverNumberId: String) {
        getAllMessages(receiverNumberId)
            viewModelScope.launch {
                when(val result = chatSocketService.initSession()) {
                    is Resource.Success -> {
                        chatSocketService.observeMessages()
                            .onEach { message ->

                                val newList = state.value.messages.toMutableList().apply {
                                    add(0, message)
                                }
                                _state.update {  state.value.copy(
                                    messages = newList
                                )}

                            }.launchIn(viewModelScope)
                    }
                    is Resource.Error -> {
                        _toastEvent.emit(result.message ?: "Unknown error")
                    }
                }
            }

    }

    fun setName(name : String)
    {
        if (name.isEmpty()) return
        _name.update { name }
    }

    fun onMessageChange(message: String) {
        _messageText.update { message }
    }

    fun disconnect() {
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }

    fun getAllMessages(receiverNumberId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = messageService.getAllMessages(receiverNumberId)
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )
        }
    }

    fun sendMessage(receiverNumberId : String) {
        viewModelScope.launch {
            if(messageText.value.isNotBlank()) {
                chatSocketService.sendMessage(messageText.value,receiverNumberId)
                _messageText.update { "" }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}