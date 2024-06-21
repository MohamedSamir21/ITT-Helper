package com.example.itthelper.career_guidance_hub.presentation.chat_bot

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.career_guidance_hub.domain.usecase.SendChatBotMessage
import com.example.itthelper.core.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor(
    private val sendChatBotMessage: SendChatBotMessage
) : ViewModel() {
    private val _state = mutableStateOf(
        ChatBotScreenState()
    )
    val state = _state

    fun onEvent(event: ChatBotScreenEvent) {
        when (event) {
            is ChatBotScreenEvent.ClientMessage -> {
                _state.value = state.value.copy(
                    clientMessage = event.message
                )
            }

            ChatBotScreenEvent.Send -> {
                sendMessage()
            }
        }
    }

    private fun sendMessage() {
        _state.value = state.value.copy(
            messages = state.value.messages + mapOf(
                "client" to state.value.clientMessage
            ),
            isReplaying = true
        )

        viewModelScope.launch {
            when (val replay = sendChatBotMessage(state.value.clientMessage)) {
                is Result.Success -> {
                    replay.data.collect {
                        _state.value = state.value.copy(
                            messages = state.value.messages + mapOf(
                                "bot" to it
                            ),
                            isReplaying = false
                        )
                    }
                }

                else -> Unit
            }
        }
        _state.value = state.value.copy(
            clientMessage = ""
        )
    }

    init {
        viewModelScope.launch {
            delay(1000)
            _state.value = state.value.copy(
                messages = mutableListOf(
                    mapOf(
                        "bot" to "Hello! I am here to support you.",
                    ),
                    mapOf(
                        "bot" to "Ask me a question"
                    )
                ),
                isReplaying = false
            )
        }
    }
}