package com.apptikar.feature.chat.presentation.callslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.feature.chat.domain.model.UserResponse
import com.apptikar.feature.chat.domain.usecases.SearchUserByNumberIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val searchUserByNumberIdUseCase: SearchUserByNumberIdUseCase) : ViewModel() {

    private val _contactsList  = MutableStateFlow<List<UserResponse>>(listOf())
    val contactsList = _contactsList.asStateFlow()

    private val _searchNumber = MutableStateFlow("")
    val searchNumber  = _searchNumber.asStateFlow()


    fun setSearchNumber(number : String)
    {
        _searchNumber.update { number }
    }

    @OptIn(FlowPreview::class)
    private fun search()
    {
        viewModelScope.launch {
            _searchNumber.debounce(1500).distinctUntilChanged().collectLatest {
                if (it.isNotEmpty())
                {
                    _contactsList.update {  searchUserByNumberIdUseCase.invoke(_searchNumber.value)}
                }

            }
        }
    }

    init {
        search()
    }

}