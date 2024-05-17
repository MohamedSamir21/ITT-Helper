package com.example.itthelper.career_guidance_hub.data.source.remote

import com.example.itthelper.career_guidance_hub.data.model.CourseEntity
import retrofit2.http.GET

interface CourseApi {
    @GET("api/courses/")
    suspend fun getCourses(): List<CourseEntity>
}