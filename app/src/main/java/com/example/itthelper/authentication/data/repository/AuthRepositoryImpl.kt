package com.example.itthelper.authentication.data.repository

import com.example.itthelper.authentication.data.local.DataStoreRepository
import com.example.itthelper.authentication.data.remote.AuthenticationApi
import com.example.itthelper.authentication.data.remote.SignInRequest
import com.example.itthelper.authentication.data.remote.SignUpRequest
import com.example.itthelper.authentication.domain.model.LoginUserData
import com.example.itthelper.authentication.domain.model.RegisterUserData
import com.example.itthelper.authentication.domain.repository.AuthRepository
import com.example.itthelper.authentication.domain.result.AuthResult
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val authenticationApi: AuthenticationApi,
    private val dataStore: DataStoreRepository
) : AuthRepository {

    override suspend fun saveOnWelcomeDone(done: Boolean) {
        dataStore.saveOnWelcomeDone(done)
    }

    override suspend fun readWelcomeDoneStatus(): Boolean {
        return dataStore.readWelcomeDoneStatus()
    }

    override suspend fun registerNewUser(userData: RegisterUserData): AuthResult<Unit> {
        return try {
            val registerRequest = SignUpRequest(
                nickname = userData.nickName,
                email = userData.email,
                password = userData.password,
                acceptedTerms = userData.acceptedTerms
            )
            authenticationApi.register(registerRequest)
            loginUser(
                userData = LoginUserData(
                    email = userData.email,
                    password = userData.password
                )
            )
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

    override suspend fun loginUser(userData: LoginUserData): AuthResult<Unit> {
        return try {
            val loginRequest = SignInRequest(
                email = userData.email,
                password = userData.password
            )
            val tokenResponse = authenticationApi.login(loginRequest)
            dataStore.saveTokenOnLoginDone(tokenResponse.token)
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

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val savedToken = dataStore.readLoginToken() ?: return AuthResult.Unauthorized()
            authenticationApi.authenticate("Bearer $savedToken")
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