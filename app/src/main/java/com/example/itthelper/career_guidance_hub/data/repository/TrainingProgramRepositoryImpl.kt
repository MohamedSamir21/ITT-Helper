package com.example.itthelper.career_guidance_hub.data.repository

import com.example.itthelper.career_guidance_hub.data.model.CompanyEntity
import com.example.itthelper.career_guidance_hub.data.model.TrainingProgramEntity
import com.example.itthelper.career_guidance_hub.data.source.remote.TrainingProgramApi
import com.example.itthelper.career_guidance_hub.domain.model.TrainingProgram
import com.example.itthelper.career_guidance_hub.domain.repository.TrainingProgramRepository
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

class TrainingProgramRepositoryImpl(
    private val trainingProgramApi: TrainingProgramApi,
    private val dataStore: DataStoreRepository
) : TrainingProgramRepository {

    override suspend fun getTrainingPrograms(): Result<Flow<List<TrainingProgram>>, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val trainingPrograms = mergeTrainingProgramsWithAssociatedCompanies(
                    getTrainingProgramEntities(),
                    getCompanyEntities()
                )
                Result.Success(
                    data = flow {
                        emit(trainingPrograms)
                    }
                )
            } catch (ex: HttpException) {
                val errorType = ex.code().getHttpErrorType()
                if (errorType == DataError.Network.UNAUTHORIZED)
                    dataStore.saveOnLoginDone(false)
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

    private suspend fun getTrainingProgramEntities(): List<TrainingProgramEntity> {
        return withContext(Dispatchers.IO) {
            trainingProgramApi.getTrainingProgramEntities()
        }
    }

    private suspend fun getCompanyEntities(): List<CompanyEntity> {
        return withContext(Dispatchers.IO) {
            trainingProgramApi.getCompanies()
        }
    }

    private fun mergeTrainingProgramsWithAssociatedCompanies(
        trainingProgramEntities: List<TrainingProgramEntity>,
        companyEntities: List<CompanyEntity>
    ): List<TrainingProgram> {
        val trainingPrograms = mutableListOf<TrainingProgram>()
        trainingProgramEntities.forEachIndexed { index, trainingProgramEntity ->
            val companyEntity = companyEntities.find {
                trainingProgramEntity.company.contains(it.id)
            }
            val trainingProgram = TrainingProgram(
                trainingProgramEntity.name,
                trainingProgramEntity.time,
                trainingProgramEntity.place,
                companyEntity?.name
            )
            trainingPrograms.add(
                index,
                trainingProgram
            )
        }
        return trainingPrograms.toList()
    }
}