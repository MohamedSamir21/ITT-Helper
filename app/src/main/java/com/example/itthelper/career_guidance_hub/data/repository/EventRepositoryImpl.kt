package com.example.itthelper.career_guidance_hub.data.repository

import com.example.itthelper.career_guidance_hub.data.mapper.toDomainEvents
import com.example.itthelper.career_guidance_hub.data.source.remote.api.EventApi
import com.example.itthelper.career_guidance_hub.domain.model.Event
import com.example.itthelper.career_guidance_hub.domain.repository.EventRepository
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

class EventRepositoryImpl(
    private val eventApi: EventApi,
    private val dataStore: DataStoreRepository
) : EventRepository {

    override suspend fun getEvents(): Result<Flow<List<Event>>, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val eventEntities = eventApi.getEvents()
                Result.Success(
                    data = flow { emit(eventEntities.toDomainEvents()) }
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