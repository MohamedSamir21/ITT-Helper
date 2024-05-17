package com.example.itthelper.career_guidance_hub.domain.usecase

import com.example.itthelper.career_guidance_hub.domain.model.TrainingProgram
import com.example.itthelper.career_guidance_hub.domain.repository.TrainingProgramRepository
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrainingProgramsUseCase @Inject constructor(
    private val trainingProgramRepository: TrainingProgramRepository
) {
    suspend operator fun invoke(): Result<Flow<List<TrainingProgram>>, DataError.Network> {
        return trainingProgramRepository.getTrainingPrograms()
    }
}