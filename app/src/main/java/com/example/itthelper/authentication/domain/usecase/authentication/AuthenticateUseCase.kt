package com.example.itthelper.authentication.domain.usecase.authentication

import com.example.itthelper.authentication.domain.repository.AuthRepository
import com.example.itthelper.authentication.domain.result.AuthResult
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): AuthResult<Unit> {
        return authRepository.authenticate()
    }
}