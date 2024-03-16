package com.example.itthelper.authentication.di

import android.content.Context
import com.example.itthelper.authentication.data.repository.DataStoreRepositoryImpl
import com.example.itthelper.authentication.data.local.DataStoreRepository
import com.example.itthelper.authentication.data.remote.AuthenticationApi
import com.example.itthelper.authentication.data.repository.AuthRepositoryImpl
import com.example.itthelper.authentication.domain.repository.AuthRepository
import com.example.itthelper.authentication.domain.validation.EmailValidator
import com.example.itthelper.authentication.presentation.util.EmailValidatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        api: AuthenticationApi,
        dataStoreRepository: DataStoreRepository
    ): AuthRepository {
        return AuthRepositoryImpl(api, dataStoreRepository)
    }


    @Provides
    @Singleton
    fun provideEmailValidator(): EmailValidator {
        return EmailValidatorImpl()
    }
}