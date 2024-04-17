package com.example.itthelper.authentication.domain.model

data class RegisterUserData(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmedPassword: String = "",
    val phoneNumber: String = "",
    val acceptedTerms: Boolean = false
)
