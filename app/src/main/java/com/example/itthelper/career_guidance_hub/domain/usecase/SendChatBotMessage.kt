package com.example.itthelper.career_guidance_hub.domain.usecase

import com.example.itthelper.career_guidance_hub.domain.repository.ChatBotRepository
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendChatBotMessage @Inject constructor(
    private val chatBotRepo: ChatBotRepository
) {
    suspend operator fun invoke(message: String): Result<Flow<String>, DataError.Network> {
        return chatBotRepo.sendMessage(message)
    }
}