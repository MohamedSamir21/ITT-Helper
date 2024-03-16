package com.example.itthelper.authentication.presentation.register

import com.example.itthelper.authentication.domain.model.RegisterUserData

data class RegisterScreenState(
    val userData: RegisterUserData,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmedPasswordError: String? = null,
    val acceptedTermsError: String? = null
)
