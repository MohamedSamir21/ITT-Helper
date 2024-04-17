package com.example.itthelper.authentication.data.remote

import com.squareup.moshi.Json

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    @field:Json(name = "phone_number")
    val phoneNumber: String
)
