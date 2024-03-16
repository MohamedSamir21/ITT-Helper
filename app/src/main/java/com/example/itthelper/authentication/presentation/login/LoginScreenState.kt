package com.example.itthelper.authentication.presentation.login

import com.example.itthelper.authentication.domain.model.LoginUserData

data class LoginScreenState(
    val userData: LoginUserData,
    val emailError: String? = null
)
