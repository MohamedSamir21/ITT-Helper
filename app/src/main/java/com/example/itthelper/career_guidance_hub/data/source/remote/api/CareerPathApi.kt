package com.example.itthelper.career_guidance_hub.data.source.remote.api

import com.example.itthelper.career_guidance_hub.data.model.CareerPathEntity
import retrofit2.http.GET

interface CareerPathApi {
    @GET("api/paths/")
    suspend fun getCareerPaths(): List<CareerPathEntity>
}