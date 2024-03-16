package com.example.itthelper.authentication.data.local

interface DataStoreRepository {

    suspend fun saveOnWelcomeDone(done: Boolean)
    suspend fun readWelcomeDoneStatus(): Boolean
    suspend fun saveTokenOnLoginDone(token: String)
    suspend fun readLoginToken(): String?
}