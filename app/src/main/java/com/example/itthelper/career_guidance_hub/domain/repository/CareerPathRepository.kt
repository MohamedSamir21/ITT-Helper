package com.example.itthelper.career_guidance_hub.domain.repository

import com.example.itthelper.career_guidance_hub.domain.model.CareerPath
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface CareerPathRepository {
    suspend fun getCareerPaths(): Result<Flow<List<CareerPath>>, DataError.Network>
}