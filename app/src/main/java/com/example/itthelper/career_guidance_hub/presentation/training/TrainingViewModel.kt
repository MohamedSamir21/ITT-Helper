package com.example.itthelper.career_guidance_hub.presentation.training

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.career_guidance_hub.domain.usecase.GetCoursesUseCase
import com.example.itthelper.career_guidance_hub.domain.usecase.GetTrainingProgramsUseCase
import com.example.itthelper.career_guidance_hub.presentation.util.asUiText
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val getTrainingProgramsUseCase: GetTrainingProgramsUseCase,
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(
        TrainingScreenState(
            programs = emptyList(),
            courses = emptyList()
        )
    )
    val state: State<TrainingScreenState>
        get() = _state
    private val unauthorizedChannel =
        MutableSharedFlow<TrainingScreenEvent.Unauthorized>(replay = 1)
    val unauthorizedResult = unauthorizedChannel.asSharedFlow()

    private fun getPrograms() {
        viewModelScope.launch {
            when (val result = getTrainingProgramsUseCase()) {
                is Result.Success -> {
                    result.data.collect {
                        _state.value = state.value.copy(
                            programs = it,
                            areProgramsLoading = false
                        )
                    }
                }

                is Result.Failure -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        unauthorizedChannel.emit(TrainingScreenEvent.Unauthorized(message = result.error.asUiText()))
                    }
                    _state.value = state.value.copy(
                        errorOfLoadingPrograms = result.error.asUiText(),
                        areProgramsLoading = false
                    )
                }
            }

        }
    }

    private fun getCourses() {
        viewModelScope.launch {
            when (val result = getCoursesUseCase()) {
                is Result.Success -> {
                    result.data.collect {
                        _state.value = state.value.copy(
                            courses = it,
                            areCoursesLoading = false
                        )
                    }
                }

                is Result.Failure -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        unauthorizedChannel.emit(TrainingScreenEvent.Unauthorized(message = result.error.asUiText()))
                    }
                    _state.value = state.value.copy(
                        errorOfLoadingCourses = result.error.asUiText(),
                        areCoursesLoading = false
                    )
                }
            }
        }
    }

    private fun loadData() {
        getPrograms()
        getCourses()
    }

    fun onEvent(event: TrainingScreenEvent) {
        when (event) {
            TrainingScreenEvent.RetryLoadingPrograms -> {
                retryLoadingPrograms()
            }

            TrainingScreenEvent.RetryLoadingCourses -> {
                retryLoadingCourses()
            }

            else -> Unit
        }
    }

    private fun retryLoadingPrograms() {
        _state.value = state.value.copy(
            errorOfLoadingPrograms = null,
            areProgramsLoading = true
        )
        getPrograms()
    }

    private fun retryLoadingCourses() {
        _state.value = state.value.copy(
            errorOfLoadingCourses = null,
            areCoursesLoading = true
        )
        getCourses()
    }

    init {
        loadData()
    }
}