package com.example.itthelper.career_guidance_hub.presentation.contact

import com.example.itthelper.career_guidance_hub.presentation.util.UiText

sealed class ContactScreenEvent {
    data class Name(val value: String) : ContactScreenEvent()
    data class Email(val value: String) : ContactScreenEvent()
    data class Phone(val value: String) : ContactScreenEvent()
    data class Subject(val value: String) : ContactScreenEvent()
    data class Message(val value: String) : ContactScreenEvent()
    data class Unauthorized(val message: UiText) : ContactScreenEvent()
    object HideResultDialog : ContactScreenEvent()
    object Send : ContactScreenEvent()
}