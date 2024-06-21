package com.example.itthelper.career_guidance_hub.presentation.feedback

import com.example.itthelper.career_guidance_hub.presentation.contact.ContactScreenEvent
import com.example.itthelper.career_guidance_hub.presentation.util.UiText

sealed class FeedbackScreenEvent {
    data class Name(val value: String) : FeedbackScreenEvent()
    data class Email(val value: String) : FeedbackScreenEvent()
    data class Phone(val value: String) : FeedbackScreenEvent()
    data class Position(val value: String) : FeedbackScreenEvent()
    data class Type(val value: String) : FeedbackScreenEvent()
    data class Date(val value: String) : FeedbackScreenEvent()
    data class Message(val value: String) : FeedbackScreenEvent()
    data class Unauthorized(val message: UiText) : FeedbackScreenEvent()
    object TogglePositionDropdownMenu : FeedbackScreenEvent()
    object ToggleTypeDropdownMenu : FeedbackScreenEvent()
    object ToggleDatePicker : FeedbackScreenEvent()
    object Reset : FeedbackScreenEvent()
    object Send : FeedbackScreenEvent()
    object HideResultDialog : FeedbackScreenEvent()
}