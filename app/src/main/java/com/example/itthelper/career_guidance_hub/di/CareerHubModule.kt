package com.example.itthelper.career_guidance_hub.di

import com.example.itthelper.career_guidance_hub.data.repository.CareerPathRepositoryImpl
import com.example.itthelper.career_guidance_hub.data.repository.ContactUsRepositoryImpl
import com.example.itthelper.career_guidance_hub.data.repository.CourseRepositoryImpl
import com.example.itthelper.career_guidance_hub.data.repository.EventRepositoryImpl
import com.example.itthelper.career_guidance_hub.data.repository.LogoutRepositoryImpl
import com.example.itthelper.career_guidance_hub.data.repository.TrainingProgramRepositoryImpl
import com.example.itthelper.career_guidance_hub.data.source.remote.api.CareerPathApi
import com.example.itthelper.career_guidance_hub.data.source.remote.api.ContactUsApi
import com.example.itthelper.career_guidance_hub.data.source.remote.api.CourseApi
import com.example.itthelper.career_guidance_hub.data.source.remote.api.EventApi
import com.example.itthelper.career_guidance_hub.data.source.remote.api.LogoutApi
import com.example.itthelper.career_guidance_hub.data.source.remote.api.TrainingProgramApi
import com.example.itthelper.career_guidance_hub.domain.repository.CareerPathRepository
import com.example.itthelper.career_guidance_hub.domain.repository.ContactUsRepository
import com.example.itthelper.career_guidance_hub.domain.repository.CourseRepository
import com.example.itthelper.career_guidance_hub.domain.repository.EventRepository
import com.example.itthelper.career_guidance_hub.domain.repository.LogoutRepository
import com.example.itthelper.career_guidance_hub.domain.repository.TrainingProgramRepository
import com.example.itthelper.core.data.repository.TokenRepository
import com.example.itthelper.core.data.source.local.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CareerHubModule {

    @Provides
    @Singleton
    fun provideCourseApi(retrofit: Retrofit): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCourseRepository(
        courseApi: CourseApi,
        dataStore: DataStoreRepository
    ): CourseRepository {
        return CourseRepositoryImpl(courseApi, dataStore)
    }

    @Provides
    @Singleton
    fun provideTrainingProgramApi(retrofit: Retrofit): TrainingProgramApi {
        return retrofit.create(TrainingProgramApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrainingProgramRepositoryImpl(
        trainingProgramApi: TrainingProgramApi,
        dataStore: DataStoreRepository
    ): TrainingProgramRepository {
        return TrainingProgramRepositoryImpl(trainingProgramApi, dataStore)
    }

    @Provides
    @Singleton
    fun provideCareerPathApi(retrofit: Retrofit): CareerPathApi {
        return retrofit.create(CareerPathApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCareerPathRepository(
        careerPathApi: CareerPathApi,
        dataStore: DataStoreRepository
    ): CareerPathRepository {
        return CareerPathRepositoryImpl(careerPathApi, dataStore)
    }

    @Provides
    @Singleton
    fun provideEventApi(retrofit: Retrofit): EventApi {
        return retrofit.create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventRepositoryImp(
        eventApi: EventApi,
        dataStore: DataStoreRepository
    ): EventRepository {
        return EventRepositoryImpl(eventApi, dataStore)
    }

    @Provides
    @Singleton
    fun provideLogoutApi(retrofit: Retrofit): LogoutApi {
        return retrofit.create(LogoutApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLogoutRepository(
        logoutApi: LogoutApi,
        tokenRepository: TokenRepository,
        dataStore: DataStoreRepository
    ): LogoutRepository {
        return LogoutRepositoryImpl(logoutApi, tokenRepository, dataStore)
    }

    @Provides
    @Singleton
    fun provideContactUsApi(retrofit: Retrofit): ContactUsApi {
        return retrofit.create(ContactUsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContactUsRepository(
        contactUsApi: ContactUsApi,
        dataStore: DataStoreRepository
    ): ContactUsRepository {
        return ContactUsRepositoryImpl(
            contactUsApi,
            dataStore
        )
    }

}