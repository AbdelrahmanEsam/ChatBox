package com.apptikar.domain.usecases
import android.util.Log
import com.apptikar.domain.repositories.SignRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignRepository){

    suspend operator fun invoke(numberId : String , username : String , password : String )  : Boolean{
        Log.d("readDataOperations",repository.toString())
       return repository.signUp(numberId = numberId,username = username, password =  password)
    }
    }

