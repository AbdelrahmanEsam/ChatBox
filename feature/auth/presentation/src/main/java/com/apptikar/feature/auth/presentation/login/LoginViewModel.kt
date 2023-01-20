package com.apptikar.feature.auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.core.common.IoDispatcher
import com.apptikar.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val SignInUseCase: SignInUseCase,
) : ViewModel() {


    private val _numberId = MutableStateFlow("")
    val numberId: StateFlow<String> = _numberId

    private val _password = MutableStateFlow("")
    val password : StateFlow<String> = _password

    private val _successful = MutableStateFlow(false)
    val successful : StateFlow<Boolean> = _successful.asStateFlow()



    fun onNumberChangeChange(username: String) {
        _numberId.update { username }
    }

    fun onPasswordChange(password: String) {
        _password.update { password }
    }

    fun signIn()
    {
        viewModelScope.launch(ioDispatcher) {
            _successful.update {  SignInUseCase(numberId.value,password.value)}
        }
    }
}