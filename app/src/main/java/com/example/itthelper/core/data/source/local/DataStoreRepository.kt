package com.example.itthelper.core.data.source.local

interface DataStoreRepository {
    suspend fun saveOnWelcomeDone(done: Boolean)
    suspend fun readWelcomeDoneStatus(): Boolean
    suspend fun saveOnLoginDone(done: Boolean)
    suspend fun readLoginDoneStatus(): Boolean
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun readAccessToken(): String?
    suspend fun readRefreshToken(): String?
}