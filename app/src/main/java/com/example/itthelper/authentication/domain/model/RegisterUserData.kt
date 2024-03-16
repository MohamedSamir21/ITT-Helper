package com.example.itthelper.authentication.domain.model

data class RegisterUserData(
    val nickName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmedPassword: String = "",
    val acceptedTerms: Boolean = false
)
