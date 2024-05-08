package com.example.itthelper.career_guidance_hub.presentation.feedback

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FeedbackViewModel : ViewModel() {
    private val _state = mutableStateOf(
        FeedbackScreenState()
    )
    val state: State<FeedbackScreenState>
        get() = _state

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
                Log.d("FeedbackViewModel", state.datePickerState.selectedDate)
            }

            is FeedbackScreenEvent.ToggleDatePicker -> {
                _state.value = state.copy(
                    datePickerState = datePickerState.copy(
                        showDatePicker = datePickerState.showDatePicker.not()
                    )
                )
                Log.d("FeedbackViewModel", state.datePickerState.showDatePicker.toString())
            }

            FeedbackScreenEvent.Reset -> {
                resetEnteredData()
            }

            FeedbackScreenEvent.Send -> {

            }
        }
    }

    private fun resetEnteredData() {
        _state.value = FeedbackScreenState()
    }
}