package com.example.itthelper.career_guidance_hub.data.repository

import com.example.itthelper.BuildConfig
import com.example.itthelper.career_guidance_hub.data.source.remote.request.voice_flow.Request
import com.example.itthelper.career_guidance_hub.data.source.remote.api.VoiceFlowApi
import com.example.itthelper.career_guidance_hub.data.source.remote.request.voice_flow.VoiceFlowRequest
import com.example.itthelper.career_guidance_hub.data.source.remote.response.voice_flow.VoiceFlowResponse
import com.example.itthelper.career_guidance_hub.domain.repository.ChatBotRepository
import com.example.itthelper.core.data.network.exception.NoConnectivityException
import com.example.itthelper.core.data.utility.getHttpErrorType
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class VoiceFlowRepositoryImpl(
    private val voiceFlowApi: VoiceFlowApi
) : ChatBotRepository {

    override suspend fun sendMessage(message: String): Result<Flow<String>, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val request = VoiceFlowRequest(
                    Request(
                        type = "text",
                        payload = message
                    )
                )
                val responses = voiceFlowApi.sendMessage(
                    apiKey = BuildConfig.VOICE_FLOW_API_KEY,
                    request = request
                )
                val voiceFlowResponses = responses.getVoiceFlowResponse()

                Result.Success(data = flow {
                    voiceFlowResponses.forEach { response ->
                        if (response.type == "text") {
                            response.payload.message?.let { emit(it) }
                        }
                    }
                })
            } catch (ex: HttpException) {
                val errorType = ex.code().getHttpErrorType()
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

    private fun List<VoiceFlowResponse>.getVoiceFlowResponse(): List<VoiceFlowResponse> {
        val gson = Gson()
        val responseType = object : TypeToken<List<VoiceFlowResponse>>() {}.type
        val jsonFormattedResponse = gson.toJson(this)
        return gson.fromJson(jsonFormattedResponse, responseType)
    }
}