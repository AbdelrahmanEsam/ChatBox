package com.apptikar.domain.usecases


import android.util.Log
import com.apptikar.domain.repositories.SignRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: SignRepository) {

     suspend operator fun invoke(numberId : String, password : String)  : Boolean{
      return repository.signIn(numberId,password)
    }
}