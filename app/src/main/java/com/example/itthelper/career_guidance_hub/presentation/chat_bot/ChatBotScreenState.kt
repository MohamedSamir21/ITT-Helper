package com.example.itthelper.career_guidance_hub.presentation.chat_bot

data class ChatBotScreenState(
    val clientMessage: String = "",
    val messages: List<Map<String, String>> = mutableListOf(),
    var isReplaying: Boolean = true
)