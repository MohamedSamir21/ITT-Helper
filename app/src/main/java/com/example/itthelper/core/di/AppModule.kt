package com.example.itthelper.core.di

import android.content.Context
import com.example.itthelper.core.data.source.local.DataStoreRepository
import com.example.itthelper.core.data.network.interceptor.AccessTokenInterceptor
import com.example.itthelper.core.data.network.interceptor.NetworkConnectivityInterceptor
import com.example.itthelper.core.data.repository.NetworkConnectivityManager
import com.example.itthelper.core.data.network.interceptor.RefreshTokenInterceptor
import com.example.itthelper.core.data.repository.DataStoreRepositoryImpl
import com.example.itthelper.core.data.repository.TokenRepository
import com.example.itthelper.core.data.source.remote.TokenApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccessTokenInterceptor(
        tokenRepository: TokenRepository
    ): AccessTokenInterceptor {
        return AccessTokenInterceptor(tokenRepo = tokenRepository)
    }

    @Provides
    @Singleton
    fun provideRefreshTokenInterceptor(
        tokenRepository: TokenRepository
    ): RefreshTokenInterceptor {
        return RefreshTokenInterceptor(tokenRepo = tokenRepository)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityInterceptor(
        connectivityManager: NetworkConnectivityManager
    ): NetworkConnectivityInterceptor {
        return NetworkConnectivityInterceptor(connectivityManager)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        connectivityInterceptor: NetworkConnectivityInterceptor,
        accessTokenInterceptor: AccessTokenInterceptor,
        refreshTokenInterceptor: RefreshTokenInterceptor,
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(accessTokenInterceptor)
            .authenticator(refreshTokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("without_interceptors")
    fun provideRetrofitWithoutInterceptors(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }

    @IOCoroutineScope
    @Provides
    @Singleton
    fun provideIOCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Provides
    @Singleton
    fun provideTokenRepository(
        tokenApi: TokenApi,
        dataStore: DataStoreRepository,
        @IOCoroutineScope
        ioCoroutineScope: CoroutineScope
    ): TokenRepository {
        return TokenRepository(tokenApi, dataStore, ioCoroutineScope)
    }

    @Provides
    @Singleton
    fun provideTokenApi(@Named("without_interceptors") retrofit: Retrofit): TokenApi {
        return retrofit.create(TokenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityManager(
        @ApplicationContext context: Context
    ): NetworkConnectivityManager {
        return NetworkConnectivityManager(context)
    }
}