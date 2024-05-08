package com.example.itthelper.career_guidance_hub.presentation.contact

sealed class ContactScreenEvent {
    data class Name(val value: String) : ContactScreenEvent()
    data class Email(val value: String) : ContactScreenEvent()
    data class Phone(val value: String) : ContactScreenEvent()
    data class Subject(val value: String) : ContactScreenEvent()
    data class Message(val value: String) : ContactScreenEvent()
    object Send : ContactScreenEvent()
}