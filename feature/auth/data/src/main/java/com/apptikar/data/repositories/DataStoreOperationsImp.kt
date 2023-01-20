package com.apptikar.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.apptikar.domain.repositories.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreOperationsImp @Inject constructor(
private val dataStore : DataStore<Preferences>
) : DataStoreOperations {

    private object PreferenceKey{
        val signInKey = stringPreferencesKey("SignIn Preference")
    }


    override suspend fun saveSignInState(token: String) {
            dataStore.edit { preferences ->
                preferences[PreferenceKey.signInKey] = token
            }
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