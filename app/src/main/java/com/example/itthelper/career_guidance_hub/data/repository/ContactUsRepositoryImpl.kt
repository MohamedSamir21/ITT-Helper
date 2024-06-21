package com.example.itthelper.career_guidance_hub.data.repository

import com.example.itthelper.career_guidance_hub.data.mapper.toDataModel
import com.example.itthelper.career_guidance_hub.data.source.remote.api.ContactUsApi
import com.example.itthelper.career_guidance_hub.domain.model.ContactUs
import com.example.itthelper.career_guidance_hub.domain.repository.ContactUsRepository
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

class ContactUsRepositoryImpl(
    private val contactUsApi: ContactUsApi,
    private val dataStore: DataStoreRepository
) : ContactUsRepository {
    override suspend fun sendMessage(contactUs: ContactUs): Result<Flow<String>, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val response = contactUsApi.sendMessage(
                    contactUs.toDataModel()
                )
                Result.Success(
                    data = flow { emit(response.message) }
                )
            } catch (ex: HttpException) {
                val errorType = ex.code().getHttpErrorType()
                if (errorType == DataError.Network.UNAUTHORIZED) {
                    dataStore.saveOnLoginDone(false)
                }
                Result.Failure(error = errorType)
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