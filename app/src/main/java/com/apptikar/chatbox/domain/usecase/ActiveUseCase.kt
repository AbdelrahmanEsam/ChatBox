package com.apptikar.chatbox.domain.usecase

import android.util.Log
import com.apptikar.chatbox.domain.repositories.ActiveRepository
import javax.inject.Inject

class ActiveUseCase @Inject constructor(private val repository: ActiveRepository) {

    suspend operator fun invoke() : Boolean {
        return repository.active()
    }
}