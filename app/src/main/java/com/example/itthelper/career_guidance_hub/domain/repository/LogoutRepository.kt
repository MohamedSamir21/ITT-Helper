package com.example.itthelper.career_guidance_hub.domain.repository

import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result

interface LogoutRepository {
    suspend fun logout(): Result<Unit, DataError.Network>
}