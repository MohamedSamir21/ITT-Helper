package com.example.itthelper.authentication.di

import com.example.itthelper.authentication.data.remote.AuthenticationApi
import com.example.itthelper.authentication.data.repository.AuthRepositoryImpl
import com.example.itthelper.authentication.domain.repository.AuthRepository
import com.example.itthelper.authentication.domain.validation.EmailValidator
import com.example.itthelper.authentication.domain.validation.PhoneValidator
import com.example.itthelper.authentication.presentation.util.EmailValidatorImpl
import com.example.itthelper.authentication.presentation.util.PhoneValidatorImpl
import com.example.itthelper.core.data.source.local.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthenticationApi(@Named("without_interceptors") retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
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

    @Provides
    @Singleton
    fun providePhoneValidator(): PhoneValidator {
        return PhoneValidatorImpl()
    }
}