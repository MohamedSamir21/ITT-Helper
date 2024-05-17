package com.example.itthelper.career_guidance_hub.domain.usecase

import com.example.itthelper.career_guidance_hub.domain.repository.LogoutRepository
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
    private val logoutRepository: LogoutRepository
) {
    suspend operator fun invoke(): Result<Unit, DataError.Network> {
        return logoutRepository.logout()
    }
}