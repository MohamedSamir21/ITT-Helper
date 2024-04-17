package com.example.itthelper.authentication.domain.usecase.validation

import com.example.itthelper.authentication.domain.result.ValidationResult
import javax.inject.Inject

class ValidateUsernameUseCase @Inject constructor() {

    operator fun invoke(username: String): ValidationResult {
        return if (username.isBlank()) {
            ValidationResult(errorMessage = "The username cannot be empty")
        } else {
            ValidationResult(validatedData = username)
        }
    }
}