package com.example.itthelper.authentication.presentation.login

import com.example.itthelper.authentication.domain.model.LoginUserData

sealed class LoginScreenEvent{
    data class UserDataChanged(val userData: LoginUserData): LoginScreenEvent()

    object Submit: LoginScreenEvent()
}
