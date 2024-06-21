package com.example.itthelper.career_guidance_hub.domain.repository

import com.example.itthelper.career_guidance_hub.domain.model.ContactUs
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface ContactUsRepository {
    suspend fun sendMessage(contactUs: ContactUs): Result<Flow<String>, DataError.Network>
}