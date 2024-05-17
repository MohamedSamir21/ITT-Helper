package com.example.itthelper.authentication.data.repository

import android.util.Log
import com.example.itthelper.authentication.data.remote.AuthenticationApi
import com.example.itthelper.authentication.data.remote.LoginRequest
import com.example.itthelper.authentication.data.remote.RegisterRequest
import com.example.itthelper.authentication.domain.model.LoginUserData
import com.example.itthelper.authentication.domain.model.RegisterUserData
import com.example.itthelper.authentication.domain.repository.AuthRepository
import com.example.itthelper.authentication.domain.result.AuthResult
import com.example.itthelper.core.data.source.local.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val authenticationApi: AuthenticationApi,
    private val dataStore: DataStoreRepository
) : AuthRepository {

    override suspend fun saveOnWelcomeDone(done: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.saveOnWelcomeDone(done)
        }
    }

    override suspend fun readWelcomeDoneStatus(): Boolean {
        return withContext(Dispatchers.IO) {
            dataStore.readWelcomeDoneStatus()
        }
    }

    override suspend fun saveOnLoginDone(done: Boolean) {
        return withContext(Dispatchers.IO) {
            dataStore.saveOnLoginDone(done)
        }
    }

    override suspend fun readLoginDoneStatus(): Boolean {
        return withContext(Dispatchers.IO) {
            dataStore.readLoginDoneStatus()
        }
    }

    override suspend fun registerNewUser(userData: RegisterUserData): AuthResult<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val registerRequest = RegisterRequest(
                    username = userData.username,
                    email = userData.email,
                    password = userData.password,
                    phoneNumber = userData.phoneNumber
                )
                authenticationApi.register(registerRequest)
                loginUser(
                    userData = LoginUserData(
                        username = userData.username,
                        password = userData.password
                    )
                )
            } catch (ex: HttpException) {
                Log.d("AuthRepositoryImpl", ex.message.toString())
                // Provide the appropriate Http error codes handling here according to the backend
                when (ex.code()) {
                    401 -> AuthResult.Unauthorized()
                    else -> AuthResult.UnknownError()
                }
            } catch (ex: Exception) {
                AuthResult.UnknownError()
            }
        }
    }

    override suspend fun loginUser(userData: LoginUserData): AuthResult<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(
                    username = userData.username,
                    password = userData.password
                )
                val tokenResponse = authenticationApi.login(loginRequest)
                dataStore.saveOnLoginDone(true)
                dataStore.saveAccessToken(tokenResponse.access)
                dataStore.saveRefreshToken(tokenResponse.refresh)
                AuthResult.Authorized()
            } catch (ex: HttpException) {
                // Provide the appropriate Http error codes handling here according to the backend
                when (ex.code()) {
                    401 -> AuthResult.Unauthorized()
                    else -> AuthResult.UnknownError()
                }
            } catch (ex: Exception) {
                AuthResult.UnknownError()
            }
        }
    }
}