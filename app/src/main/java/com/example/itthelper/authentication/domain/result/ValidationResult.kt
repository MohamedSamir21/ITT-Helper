package com.example.itthelper.authentication.domain.result

data class ValidationResult(
    val validatedData: String? = null,
    val errorMessage: String? = null
)
