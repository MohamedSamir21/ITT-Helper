package com.example.itthelper.core.data.repository

import com.example.itthelper.core.data.source.local.DataStoreRepository
import com.example.itthelper.core.data.source.remote.RefreshRequest
import com.example.itthelper.core.data.source.remote.TokenApi
import com.example.itthelper.core.di.IOCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TokenRepository(
    private val tokenApi: TokenApi,
    private val dataStore: DataStoreRepository,
    @IOCoroutineScope
    private val scope: CoroutineScope
) {

    fun readAccessToken(): String? = runBlocking(Dispatchers.IO) {
        var accessToken: String? = null
        val job = scope.launch {
            accessToken = dataStore.readAccessToken()
        }
        job.join()
        accessToken
    }

    fun readRefreshToken(): String? = runBlocking(Dispatchers.IO) {
        var refreshToken: String? = null
        val job = scope.launch {
            refreshToken = dataStore.readRefreshToken()
        }
        job.join()
        refreshToken
    }

    fun refreshAccessToken(): String? = runBlocking(Dispatchers.IO) {
        val refreshToken = readRefreshToken()
        var newAccessToken: String? = null
        val job = refreshToken?.let {
            scope.launch {
                try {
                    newAccessToken = tokenApi.refreshAccessToken(RefreshRequest(it)).access
                    newAccessToken?.let { newAccessToken ->
                        dataStore.saveAccessToken(newAccessToken)
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
        job?.join()
        newAccessToken
    }
}