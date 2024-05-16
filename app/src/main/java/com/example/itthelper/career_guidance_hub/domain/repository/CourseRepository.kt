package com.example.itthelper.career_guidance_hub.domain.repository

import com.example.itthelper.career_guidance_hub.domain.model.Course
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourses(): Result<Flow<List<Course>>, DataError.Network>
}