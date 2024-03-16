package com.example.itthelper.authentication.domain.usecase.authentication

import com.example.itthelper.authentication.domain.model.LoginUserData
import com.example.itthelper.authentication.domain.repository.AuthRepository
import com.example.itthelper.authentication.domain.result.AuthResult
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userData: LoginUserData): AuthResult<Unit> {
        return authRepository.loginUser(userData)
    }
}