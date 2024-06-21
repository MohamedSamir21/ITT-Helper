package com.example.itthelper.career_guidance_hub.data.source.remote.api

import com.example.itthelper.career_guidance_hub.data.model.CompanyEntity
import com.example.itthelper.career_guidance_hub.data.model.TrainingProgramEntity
import retrofit2.http.GET

interface TrainingProgramApi {
    @GET("api/training/")
    suspend fun getTrainingProgramEntities(): List<TrainingProgramEntity>

    @GET("api/companies/")
    suspend fun getCompanies(): List<CompanyEntity>
}