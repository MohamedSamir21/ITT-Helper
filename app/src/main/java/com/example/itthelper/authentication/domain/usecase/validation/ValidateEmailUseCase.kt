package com.example.itthelper.authentication.domain.usecase.validation

import com.example.itthelper.authentication.domain.result.ValidationResult
import com.example.itthelper.authentication.domain.validation.EmailValidator
import com.example.itthelper.authentication.presentation.util.EmailValidatorImpl

class ValidateEmailUseCase(
    private val emailValidator: EmailValidator = EmailValidatorImpl()
) {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank())
            return ValidationResult(errorMessage = "The email cannot be empty!!")
        if (emailValidator.isValidEmail(email).not())
            return ValidationResult(errorMessage = "This is not a valid email")
        return ValidationResult(validatedData = email)
    }
}