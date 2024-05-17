package com.example.itthelper.career_guidance_hub.presentation.feedback

sealed class FeedbackScreenEvent {
    data class Name(val value: String): FeedbackScreenEvent()
    data class Email(val value: String): FeedbackScreenEvent()
    data class Phone(val value: String): FeedbackScreenEvent()
    data class Position(val value: String): FeedbackScreenEvent()
    data class Type(val value: String): FeedbackScreenEvent()
    data class Date(val value: String): FeedbackScreenEvent()
    data class Message(val value: String): FeedbackScreenEvent()
    object TogglePositionDropdownMenu: FeedbackScreenEvent()
    object ToggleTypeDropdownMenu: FeedbackScreenEvent()
    object ToggleDatePicker: FeedbackScreenEvent()
    object Reset: FeedbackScreenEvent()
    object Send: FeedbackScreenEvent()
}