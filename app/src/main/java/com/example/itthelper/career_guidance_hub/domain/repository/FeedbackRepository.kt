package com.example.itthelper.career_guidance_hub.domain.repository

import com.example.itthelper.career_guidance_hub.domain.model.Feedback
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface FeedbackRepository {
    suspend fun sendFeedback(feedback: Feedback): Result<Flow<String>, DataError.Network>
}