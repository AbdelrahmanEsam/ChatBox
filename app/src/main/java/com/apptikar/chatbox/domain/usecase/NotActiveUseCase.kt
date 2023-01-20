package com.apptikar.chatbox.domain.usecase


import com.apptikar.chatbox.domain.repositories.ActiveRepository
import javax.inject.Inject

class NotActiveUseCase @Inject constructor(private val repository: ActiveRepository) {

    suspend operator fun invoke() : Boolean {
        return repository.notActive()
    }
}