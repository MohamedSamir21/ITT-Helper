package com.example.itthelper.career_guidance_hub.domain.repository

import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface ChatBotRepository {
    suspend fun sendMessage(message: String): Result<Flow<String>, DataError.Network>
}