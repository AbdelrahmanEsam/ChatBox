package com.apptikar.chatbox.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.chatbox.domain.usecase.ActiveUseCase
import com.apptikar.chatbox.domain.usecase.NotActiveUseCase
import com.apptikar.core.common.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val activeUseCase: ActiveUseCase,
    private val notActiveUseCase: NotActiveUseCase
): ViewModel() {


    private val _successful = MutableStateFlow<Boolean?>(null)
    val successful : StateFlow<Boolean?> = _successful.asStateFlow()

   fun active()
    {
        viewModelScope.launch(ioDispatcher) {
            _successful.update {activeUseCase.invoke()}
        }.invokeOnCompletion {
            Log.d("success",successful.value.toString())
        }
    }

    fun unActive()
    {
        viewModelScope.launch(ioDispatcher) {
            val result = notActiveUseCase.invoke()
            Log.d("success",result.toString())
        }
    }





}