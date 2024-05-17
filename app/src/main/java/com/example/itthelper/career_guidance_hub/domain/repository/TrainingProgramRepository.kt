package com.example.itthelper.career_guidance_hub.domain.repository

import com.example.itthelper.career_guidance_hub.domain.model.TrainingProgram
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface TrainingProgramRepository {
    suspend fun getTrainingPrograms(): Result<Flow<List<TrainingProgram>>, DataError.Network>
}