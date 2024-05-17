package com.example.itthelper.career_guidance_hub.domain.usecase

import com.example.itthelper.career_guidance_hub.domain.model.Course
import com.example.itthelper.career_guidance_hub.domain.repository.CourseRepository
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(): Result<Flow<List<Course>>, DataError.Network> {
        return courseRepository.getCourses()
    }
}