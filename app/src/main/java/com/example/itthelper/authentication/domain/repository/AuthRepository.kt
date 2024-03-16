package com.example.itthelper.authentication.domain.repository

import com.example.itthelper.authentication.domain.model.LoginUserData
import com.example.itthelper.authentication.domain.model.RegisterUserData
import com.example.itthelper.authentication.domain.result.AuthResult

interface AuthRepository {
    suspend fun saveOnWelcomeDone(done: Boolean)
    suspend fun readWelcomeDoneStatus(): Boolean
    suspend fun registerNewUser(userData: RegisterUserData): AuthResult<Unit>
    suspend fun loginUser(userData: LoginUserData): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}