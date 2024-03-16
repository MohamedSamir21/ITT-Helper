package com.example.itthelper.authentication.domain.usecase.validation

import com.example.itthelper.authentication.domain.result.ValidationResult
import javax.inject.Inject

class ValidateConfirmedPasswordUseCase @Inject constructor() {

    operator fun invoke(password: String, confirmedPassword: String): ValidationResult {
        if (password == confirmedPassword)
            return ValidationResult(validatedData = password)
        return ValidationResult(errorMessage = "The passwords don't match!!")
    }
}