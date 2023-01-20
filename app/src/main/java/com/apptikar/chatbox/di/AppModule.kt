package com.apptikar.chatbox.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.apptikar.data.remote.ChatSocketService
import com.apptikar.data.remote.ChatSocketServiceImpl
import com.apptikar.data.remote.MessageService
import com.apptikar.data.remote.MessageServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideDataSoreObject(@ApplicationContext context: Context) : DataStore<Preferences>
            = PreferenceDataStoreFactory.create(produceFile = {context.preferencesDataStoreFile("app_preferences")})

    @Provides
    @Singleton
    fun provideHttpClient(dataStore : DataStore<Preferences>): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(HttpTimeout)
            defaultRequest { url("http://192.168.1.7:8099") }
            install(ContentNegotiation){
             json()
            }
            install(Auth)
            {
                bearer {
                    val signInKey = stringPreferencesKey("SignIn Preference")
                    runBlocking {
                        val header = dataStore.data.catch {
                            emit(emptyPreferences())
                        }.map { preferences ->
                            val signInValue = preferences[signInKey] ?: ""
                            signInValue
                        }.first()

                        if (header.isNotBlank()) {
                            loadTokens {
                                BearerTokens(accessToken = header, "")
                            }
                        }
                    }


                }

            }
        }
    }








}