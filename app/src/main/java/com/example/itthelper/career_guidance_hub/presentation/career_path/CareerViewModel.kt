package com.example.itthelper.career_guidance_hub.presentation.career_path

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.career_guidance_hub.domain.usecase.GetCareerPathsUseCase
import com.example.itthelper.career_guidance_hub.presentation.util.asUiText
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CareerViewModel @Inject constructor(
    private val getCareerPathsUseCase: GetCareerPathsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(
        CareerScreenState(
            paths = emptyList(),
            careerBannerItems = CareerBanner.items
        )
    )
    val state: State<CareerScreenState>
        get() = _state
    private val unauthorized = Channel<CareerScreenEvent.Unauthorized>()
    val unauthorizedResult = unauthorized.receiveAsFlow()

    fun onEvent(event: CareerScreenEvent) {
        if (event == CareerScreenEvent.Retry) {
            retry()
        }
    }

    private fun retry() {
        _state.value = state.value.copy(
            error = null,
            isLoading = true
        )
        getCareerPaths()
    }

    private fun getCareerPaths() {
        viewModelScope.launch {
            when (val result = getCareerPathsUseCase()) {
                is Result.Success -> {
                    result.data.collect {
                        _state.value = state.value.copy(
                            paths = it,
                            isLoading = false
                        )
                    }
                }

                is Result.Failure -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        unauthorized.send(CareerScreenEvent.Unauthorized(message = result.error.asUiText()))
                    }
                    _state.value = state.value.copy(
                        error = result.error.asUiText(),
                        isLoading = false
                    )
                }
            }
        }
    }

    init {
        getCareerPaths()
    }
}