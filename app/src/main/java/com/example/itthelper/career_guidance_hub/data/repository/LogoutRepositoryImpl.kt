package com.example.itthelper.career_guidance_hub.data.repository

import com.example.itthelper.career_guidance_hub.data.source.remote.api.LogoutApi
import com.example.itthelper.career_guidance_hub.domain.repository.LogoutRepository
import com.example.itthelper.core.data.network.exception.NoConnectivityException
import com.example.itthelper.core.data.repository.TokenRepository
import com.example.itthelper.core.data.source.local.DataStoreRepository
import com.example.itthelper.core.data.source.remote.RefreshRequest
import com.example.itthelper.core.data.utility.getHttpErrorType
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class LogoutRepositoryImpl(
    private val logoutApi: LogoutApi,
    private val tokenRepository: TokenRepository,
    private val dataStore: DataStoreRepository
) : LogoutRepository {

    override suspend fun logout(): Result<Unit, DataError.Network> = withContext(Dispatchers.IO) {
        return@withContext try {
            val refreshToken = tokenRepository.readRefreshToken()
            refreshToken?.let {
                logoutApi.logout(RefreshRequest(it))
                dataStore.saveOnLoginDone(false)
            }
            Result.Success(
                data = Unit
            )
        } catch (ex: HttpException) {
            Result.Failure(
                error = ex.code().getHttpErrorType()
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