package com.example.itthelper.career_guidance_hub.domain.usecase

import com.example.itthelper.career_guidance_hub.domain.model.ContactUs
import com.example.itthelper.career_guidance_hub.domain.repository.ContactUsRepository
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendContactUsMessageUseCase @Inject constructor(
    private val contactUsRepository: ContactUsRepository
) {
    suspend operator fun invoke(contactUs: ContactUs): Result<Flow<String>, DataError.Network> {
        return contactUsRepository.sendMessage(contactUs)
    }
}