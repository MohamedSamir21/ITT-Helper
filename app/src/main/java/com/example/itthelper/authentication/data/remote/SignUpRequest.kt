package com.example.itthelper.authentication.data.remote

data class SignUpRequest(
    val nickname: String,
    val email: String,
    val password: String,
    val acceptedTerms: Boolean
)
