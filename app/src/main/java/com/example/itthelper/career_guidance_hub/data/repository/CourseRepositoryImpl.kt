package com.example.itthelper.career_guidance_hub.data.repository

import com.example.itthelper.career_guidance_hub.data.mapper.toDomainCourses
import com.example.itthelper.career_guidance_hub.data.source.remote.CourseApi
import com.example.itthelper.career_guidance_hub.domain.model.Course
import com.example.itthelper.career_guidance_hub.domain.repository.CourseRepository
import com.example.itthelper.core.data.network.exception.NoConnectivityException
import com.example.itthelper.core.data.source.local.DataStoreRepository
import com.example.itthelper.core.data.utility.getHttpErrorType
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CourseRepositoryImpl(
    private val courseApi: CourseApi,
    private val dataStore: DataStoreRepository
) : CourseRepository {

    override suspend fun getCourses(): Result<Flow<List<Course>>, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val courseEntities = courseApi.getCourses()
                Result.Success(
                    data = flow { emit(courseEntities.toDomainCourses()) }
                )
            } catch (ex: HttpException) {
                val errorType = ex.code().getHttpErrorType()
                if (errorType == DataError.Network.UNAUTHORIZED) {
                    dataStore.saveOnLoginDone(false)
                }
                Result.Failure(
                    error = errorType
                )
            } catch (ex: NoConnectivityException) {
                Result.Failure(
                    error = DataError.Network.NO_INTERNET_CONNECTION
                )
            } catch (ex: Exception) {
                Result.Failure(
                    error = DataError.Network.UNKNOWN
                )
            }
        }
    }

}