package com.example.itthelper.career_guidance_hub.presentation.courses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.career_guidance_hub.domain.usecase.GetCoursesUseCase
import com.example.itthelper.career_guidance_hub.presentation.util.asUiText
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(
        CoursesScreenState(emptyList())
    )
    val state: State<CoursesScreenState>
        get() = _state
    private val unauthorizedChannel = Channel<CoursesScreenEvent.Unauthorized>()
    val unauthorizedResult = unauthorizedChannel.receiveAsFlow()

    fun onEvent(event: CoursesScreenEvent) {
        if (event is CoursesScreenEvent.Retry) {
            retry()
        }
    }

    private fun retry() {
        _state.value = state.value.copy(
            error = null,
            isLoading = true
        )
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch {
            when (val result = getCoursesUseCase()) {
                is Result.Success -> {
                    result.data.collect {
                        _state.value = state.value.copy(
                            courses = it,
                            isLoading = false
                        )
                    }
                }

                is Result.Failure -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        unauthorizedChannel.send(CoursesScreenEvent.Unauthorized(message = result.error.asUiText()))
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
        getCourses()
    }
}