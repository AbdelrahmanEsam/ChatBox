package com.apptikar.chatbox.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.apptikar.chatbox.domain.repositories.DataStoreAuthentication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreAuthenticationImp @Inject constructor(
private val dataStore : DataStore<Preferences>
) : DataStoreAuthentication{

    private object PreferenceKey{
        val signInKey = stringPreferencesKey("SignIn Preference")
    }



    override fun readSignInState(): Flow<String> {
       return dataStore.data.catch {
           emit(emptyPreferences())
       }.map { preferences ->
           val signInValue = preferences[PreferenceKey.signInKey] ?: ""
            signInValue
       }
    }
}