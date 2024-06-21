package com.example.itthelper.career_guidance_hub.presentation.chat_bot

sealed class ChatBotScreenEvent {
    data class ClientMessage(val message: String) : ChatBotScreenEvent()
    object Send : ChatBotScreenEvent()
}