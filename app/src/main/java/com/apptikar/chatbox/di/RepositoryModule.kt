package com.apptikar.chatbox.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.apptikar.chatbox.data.remote.ActivationService
import com.apptikar.chatbox.data.remote.ActivationServiceImpl
import com.apptikar.chatbox.data.repositories.ActiveRepositoryImp
import com.apptikar.chatbox.data.repositories.DataStoreAuthenticationImp
import com.apptikar.chatbox.domain.repositories.ActiveRepository
import com.apptikar.chatbox.domain.repositories.DataStoreAuthentication
import com.apptikar.chatbox.domain.usecase.ActiveUseCase
import com.apptikar.chatbox.domain.usecase.NotActiveUseCase
import com.apptikar.data.remote.*
import com.apptikar.data.repositories.DataStoreOperationsImp
import com.apptikar.data.repository.GetChatsRepositoryImpl
import com.apptikar.data.repository.SearchUserByNumberIdRepositoryImp
import com.apptikar.data.repositories.SignRepositoryImp
import com.apptikar.domain.repositories.DataStoreOperations
import com.apptikar.domain.repositories.SignRepository
import com.apptikar.domain.usecases.SignInUseCase
import com.apptikar.domain.usecases.SignUpUseCase
import com.apptikar.feature.chat.domain.repository.GetChatsRepository
import com.apptikar.feature.chat.domain.repository.SearchRepository
import com.apptikar.feature.chat.domain.usecases.GetChatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*


@Module
@InstallIn(ViewModelComponent::class)
 class RepositoryModule {





    @ViewModelScoped
    @Provides
    fun provideDataStoreOperations (dataStore: DataStore<Preferences>) :
            DataStoreOperations = DataStoreOperationsImp(dataStore)

    @ViewModelScoped
    @Provides
    fun provideDataStoreAuthentication (dataStore: DataStore<Preferences>) :
            DataStoreAuthentication = DataStoreAuthenticationImp(dataStore)

//    @Module
//    @InstallIn(ViewModelComponent::class)
//    public interface BindsModule {
//        @Binds
//        @ViewModelScoped
//        abstract fun provideAuthRepository(authRepository: AuthRepositoryImp): AuthRepository
//    }

    @Provides
    @ViewModelScoped
    fun provideSignInRepository(dataStore: DataStoreOperations, signService: SignService) : SignRepository
    =  SignRepositoryImp(dataStore,signService)

    @Provides
    @ViewModelScoped
    fun provideSearchUserByNumberIdRepository(searchUserService: SearchUserService) : SearchRepository
    {
         return SearchUserByNumberIdRepositoryImp(searchUserService)
    }

    @Provides
    @ViewModelScoped
    fun getChatsRepository(getChatsService: GetChatsService) : GetChatsRepository
    {
        return GetChatsRepositoryImpl(getChatsService)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthenticationRepository(operations: DataStoreAuthentication,authenticationService: ActivationService) : ActiveRepository
    = ActiveRepositoryImp(operations,authenticationService)

        @Provides
        @ViewModelScoped
        fun provideSignInUseCase(signRepository: SignRepository) =
            SignInUseCase(signRepository)

//    @Provides
//    @ViewModelScoped
//    fun provideReadTokenUseCase(signRepository: SignRepository) =
//        AuthenticationUseCase(signRepository)

        @Provides
        @ViewModelScoped
        fun provideSignUpUseCase(signRepository: SignRepository) =
            SignUpUseCase(signRepository)

    @Provides
    @ViewModelScoped
    fun provideChatsUseCase(getChatsRepository: GetChatsRepository) =
        GetChatsUseCase(getChatsRepository)

    @Provides
    @ViewModelScoped
    fun provideActiveUseCase(activeRepository: ActiveRepository)
    = ActiveUseCase(activeRepository)

    @Provides
    @ViewModelScoped
    fun provideUnActiveUseCase(activeRepository: ActiveRepository)
            = NotActiveUseCase(activeRepository)



    @Provides
    @ViewModelScoped
    fun provideSignService(client: HttpClient) : SignService = SignServiceImpl(client)


    @Provides
    @ViewModelScoped
    fun provideMessageService(client: HttpClient): MessageService {
        return MessageServiceImpl(client)
    }

    @Provides
    @ViewModelScoped
    fun provideChatSocketService(client: HttpClient): ChatSocketService {
        return ChatSocketServiceImpl(client)
    }

    @Provides
    @ViewModelScoped
    fun provideSearchService(client:HttpClient) : SearchUserService
    {
        return SearchUserServiceImplementation(client)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthenticationService(client:HttpClient) : ActivationService
    {
        return ActivationServiceImpl(client)
    }

    @Provides
    @ViewModelScoped
    fun provideGetChatsService(client: HttpClient) : GetChatsService
    {
        return GetChatsServiceImp(client)
    }

//    fun provideResources(@ApplicationContext context: Context) = context.resources

}