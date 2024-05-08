package com.example.itthelper.career_guidance_hub.presentation.contact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    private val _state = mutableStateOf(
        ContactScreenState()
    )
    val state: State<ContactScreenState>
        get() = _state

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
            ContactScreenEvent.Send -> {
                sendData()
            }
        }
    }

    private fun sendData() {
        TODO("Not yet implemented")
    }
}