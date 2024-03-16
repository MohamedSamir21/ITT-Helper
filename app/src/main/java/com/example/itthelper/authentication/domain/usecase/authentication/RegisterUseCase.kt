package com.example.itthelper.authentication.domain.usecase.authentication

import com.example.itthelper.authentication.domain.model.RegisterUserData
import com.example.itthelper.authentication.domain.repository.AuthRepository
import com.example.itthelper.authentication.domain.result.AuthResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userData: RegisterUserData): AuthResult<Unit> {
        return authRepository.registerNewUser(userData)
    }
}