package com.example.itthelper.career_guidance_hub.domain.usecase

import com.example.itthelper.career_guidance_hub.domain.model.CareerPath
import com.example.itthelper.career_guidance_hub.domain.repository.CareerPathRepository
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCareerPathsUseCase @Inject constructor(
    private val careerPathRepository: CareerPathRepository
) {
    suspend operator fun invoke(): Result<Flow<List<CareerPath>>, DataError.Network> {
        return careerPathRepository.getCareerPaths()
    }
}