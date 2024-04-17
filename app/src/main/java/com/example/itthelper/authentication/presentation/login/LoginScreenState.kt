package com.example.itthelper.authentication.presentation.login

import com.example.itthelper.authentication.domain.model.LoginUserData

data class LoginScreenState(
    val userData: LoginUserData,
    val usernameError: String? = null,
    var isLoading: Boolean = false
)
