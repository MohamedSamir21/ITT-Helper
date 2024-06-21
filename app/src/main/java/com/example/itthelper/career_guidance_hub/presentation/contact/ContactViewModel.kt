package com.example.itthelper.career_guidance_hub.presentation.contact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.domain.model.ContactUs
import com.example.itthelper.career_guidance_hub.domain.usecase.SendContactUsMessageUseCase
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
class ContactViewModel @Inject constructor(
    private val sendContactUsMessageUseCase: SendContactUsMessageUseCase
) : ViewModel() {
    private val _state = mutableStateOf(
        ContactScreenState()
    )
    val state: State<ContactScreenState>
        get() = _state
    private val unauthorizedChannel = Channel<ContactScreenEvent.Unauthorized>()
    val unauthorizedResult = unauthorizedChannel.receiveAsFlow()

    fun onEvent(event: ContactScreenEvent) {
        when (event) {
            is ContactScreenEvent.Name -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }

            is ContactScreenEvent.Email -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }

            is ContactScreenEvent.Phone -> {
                _state.value = state.value.copy(
                    phone = event.value
                )
            }

            is ContactScreenEvent.Subject -> {
                _state.value = state.value.copy(
                    subject = event.value
                )
            }

            is ContactScreenEvent.Message -> {
                _state.value = state.value.copy(
                    message = event.value
                )
            }

            is ContactScreenEvent.HideResultDialog -> {
                _state.value = state.value.copy(
                    showResultDialog = false
                )
            }

            is ContactScreenEvent.Send -> {
                sendData()
            }

            else -> Unit
        }
    }

    private fun sendData() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isSending = true
            )
            val result = sendContactUsMessageUseCase(
                ContactUs(
                    name = state.value.name,
                    email = state.value.email,
                    message = state.value.message
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
                        unauthorizedChannel.send(
                            ContactScreenEvent.Unauthorized(
                                message = result.error.asUiText()
                            )
                        )
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