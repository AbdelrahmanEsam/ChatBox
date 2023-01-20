package com.apptikar.feature.chat.presentation.chatlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.core.common.IoDispatcher
import com.apptikar.feature.chat.domain.model.ChatState
import com.apptikar.feature.chat.domain.usecases.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(private val getChatsUseCase: GetChatsUseCase
, @IoDispatcher private val ioDispatcher: CoroutineDispatcher,) : ViewModel() {


    private val _recentChats = MutableStateFlow<MutableSet<ChatState>>(mutableSetOf())
    val recentChats = _recentChats.asStateFlow()

     fun getChats()
    {
        viewModelScope.launch(ioDispatcher) {
            val result = getChatsUseCase.invoke()
            Log.d("result2",result.joinToString(" "))
            _recentChats.update {
            result
            }
        }

    }


    init {
        getChats()
    }
}