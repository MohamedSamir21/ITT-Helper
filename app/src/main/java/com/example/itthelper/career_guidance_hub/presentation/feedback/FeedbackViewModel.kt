package com.example.itthelper.career_guidance_hub.presentation.feedback

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.domain.model.Feedback
import com.example.itthelper.career_guidance_hub.domain.usecase.SendFeedbackMessageUseCase
import com.example.itthelper.career_guidance_hub.presentation.util.UiText
import com.example.itthelper.career_guidance_hub.presentation.util.asUiText
import com.example.itthelper.core.domain.error.DataError
import com.example.itthelper.core.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val sendFeedbackMessageUseCase: SendFeedbackMessageUseCase
) : ViewModel() {
    private val _state = mutableStateOf(
        FeedbackScreenState()
    )
    val state: State<FeedbackScreenState>
        get() = _state
    private val unauthorizedChannel = Channel<FeedbackScreenEvent.Unauthorized>()
    val unauthorizedResult = unauthorizedChannel.receiveAsFlow()

    fun onEvent(event: FeedbackScreenEvent) {
        val state = state.value
        val positionDropdownMenuState = state.positionDropdownMenuState
        val typeDropdownMenuState = state.typeDropdownMenuState
        val datePickerState = state.datePickerState

        when (event) {
            is FeedbackScreenEvent.Name -> {
                _state.value = state.copy(
                    name = event.value
                )
            }

            is FeedbackScreenEvent.Email -> {
                _state.value = state.copy(
                    email = event.value
                )
            }

            is FeedbackScreenEvent.Phone -> {
                _state.value = state.copy(
                    phone = event.value
                )
            }

            is FeedbackScreenEvent.Message -> {
                _state.value = state.copy(
                    message = event.value
                )
            }

            is FeedbackScreenEvent.Position -> {
                _state.value = state.copy(
                    positionDropdownMenuState = positionDropdownMenuState.copy(
                        selectedPosition = event.value
                    )
                )
            }

            is FeedbackScreenEvent.TogglePositionDropdownMenu -> {
                _state.value = state.copy(
                    positionDropdownMenuState = positionDropdownMenuState.copy(
                        showPositionDropdownMenu = positionDropdownMenuState.showPositionDropdownMenu.not()
                    )
                )
            }

            is FeedbackScreenEvent.Type -> {
                _state.value = state.copy(
                    typeDropdownMenuState = typeDropdownMenuState.copy(
                        selectedType = event.value
                    )
                )
            }

            is FeedbackScreenEvent.ToggleTypeDropdownMenu -> {
                _state.value = state.copy(
                    typeDropdownMenuState = typeDropdownMenuState.copy(
                        showTypeDropdownMenu = typeDropdownMenuState.showTypeDropdownMenu.not()
                    )
                )
            }

            is FeedbackScreenEvent.Date -> {
                _state.value = state.copy(
                    datePickerState = datePickerState.copy(
                        selectedDate = event.value
                    )
                )
            }

            is FeedbackScreenEvent.ToggleDatePicker -> {
                _state.value = state.copy(
                    datePickerState = datePickerState.copy(
                        showDatePicker = datePickerState.showDatePicker.not()
                    )
                )
            }

            FeedbackScreenEvent.Reset -> {
                resetEnteredData()
            }

            FeedbackScreenEvent.Send -> {
                sendData()
            }

            FeedbackScreenEvent.HideResultDialog -> {
                _state.value = state.copy(
                    showResultDialog = false
                )
            }

            else -> Unit
        }
    }

    private fun resetEnteredData() {
        _state.value = FeedbackScreenState()
    }

    private fun sendData() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isSending = true
            )
            val result = sendFeedbackMessageUseCase(
                Feedback(
                    state.value.message
                )
            )
            when (result) {
                is Result.Success -> {
                    result.data.collect {
                        _state.value = state.value.copy(
                            dialogTitle = UiText.StringResource(R.string.success),
                            dialogBody = UiText.DynamicString(it),
                            showResultDialog = true,
                            isSending = false
                        )
                    }
                }

                is Result.Failure -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        unauthorizedChannel.send(FeedbackScreenEvent.Unauthorized(result.error.asUiText()))
                    }
                    _state.value = state.value.copy(
                        dialogTitle = UiText.StringResource(R.string.failure),
                        dialogBody = result.error.asUiText(),
                        showResultDialog = true,
                        isSending = false
                    )
                }
            }
        }
    }
}