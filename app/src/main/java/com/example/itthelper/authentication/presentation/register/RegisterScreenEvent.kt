package com.example.itthelper.authentication.presentation.register

import com.example.itthelper.authentication.domain.model.RegisterUserData

sealed class RegisterScreenEvent {
    data class UserDataChanged(val userData: RegisterUserData): RegisterScreenEvent()
    object Submit: RegisterScreenEvent()
}
