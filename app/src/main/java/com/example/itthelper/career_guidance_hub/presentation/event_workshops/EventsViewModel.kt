package com.example.itthelper.career_guidance_hub.presentation.event_workshops

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.career_guidance_hub.domain.usecase.GetEventsUseCase
import com.example.itthelper.career_guidance_hub.presentation.util.asUiText
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(
        EventsScreenState(
            events = emptyList()
        )
    )
    val state: State<EventsScreenState>
        get() = _state
    private val unauthorizedChannel = Channel<EventsScreenEvent.Unauthorized>()
    val unauthorizedResult = unauthorizedChannel.receiveAsFlow()

    fun onEvent(event: EventsScreenEvent) {
        if (event is EventsScreenEvent.Retry) {
            retry()
        }
    }

    private fun retry() {
        _state.value = state.value.copy(
            error = null,
            isLoading = true
        )
        getEvents()
    }

    private fun getEvents() {
        viewModelScope.launch {
            when (val result = getEventsUseCase()) {
                is Result.Success -> {
                    result.data.collect {
                        _state.value = state.value.copy(
                            events = it,
                            isLoading = false
                        )
                    }
                }

                is Result.Failure -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        unauthorizedChannel.send(EventsScreenEvent.Unauthorized(message = result.error.asUiText()))
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
        getEvents()
    }
}