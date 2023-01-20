package com.apptikar.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.core.common.IoDispatcher
import com.apptikar.domain.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val SignUpUseCase : SignUpUseCase,
) : ViewModel() {


    private val _username  = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _numberId = MutableStateFlow("")
    val numberId: StateFlow<String> = _numberId

    private val _password = MutableStateFlow("")
    val password : StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword : StateFlow<String> = _confirmPassword

    private val _successfulSignUp = MutableStateFlow<Boolean?>(null)
    val successfulSignUp: StateFlow<Boolean?> = _successfulSignUp

    fun onUsernameChange(username : String)
    {
        _username.update { username }
    }

    fun onNumberChangeChange(username: String) {
        _numberId.update { username }
    }

    fun onPasswordChange(password: String) {
        _password.update { password }
    }

    fun onConfirmPasswordChange(password: String) {
        _confirmPassword.update { password }
    }

    fun signUp()
    {
        if (password.value == confirmPassword.value
            && password.value.isNotBlank() && confirmPassword.value.isNotBlank() &&
                username.value.isNotBlank() && numberId.value.isNotBlank()) {
            viewModelScope.launch(ioDispatcher) {
              val result =   SignUpUseCase(
                    username = username.value,
                    password = password.value,
                    numberId = numberId.value
                )
             _successfulSignUp.update { result }
            }
        }
    }



}