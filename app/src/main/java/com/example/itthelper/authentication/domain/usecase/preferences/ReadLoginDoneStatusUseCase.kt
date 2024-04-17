package com.example.itthelper.authentication.domain.usecase.preferences

import com.example.itthelper.authentication.domain.repository.AuthRepository
import javax.inject.Inject

class ReadLoginDoneStatusUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.readLoginDoneStatus()
    }
}