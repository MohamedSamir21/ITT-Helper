package com.example.itthelper.career_guidance_hub.domain.usecase

import com.example.itthelper.career_guidance_hub.domain.model.Feedback
import com.example.itthelper.career_guidance_hub.domain.repository.FeedbackRepository
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendFeedbackMessageUseCase @Inject constructor(
    private val feedbackRepository: FeedbackRepository
) {
    suspend operator fun invoke(feedback: Feedback): Result<Flow<String>, DataError.Network> {
        return feedbackRepository.sendFeedback(feedback)
    }
}