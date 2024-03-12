package com.example.itthelper.authentication.domain.usecase.validation

import com.example.itthelper.authentication.domain.result.ValidationResult

class ValidateTermsUseCase {

    operator fun invoke(isAccepted: Boolean): ValidationResult {
        if (isAccepted)
            return ValidationResult(
                validatedData = "true"
            )
        return ValidationResult(errorMessage = "Please accept our terms and conditions.")
    }
}