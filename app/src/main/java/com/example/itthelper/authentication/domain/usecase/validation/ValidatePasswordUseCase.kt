package com.example.itthelper.authentication.domain.usecase.validation

import com.example.itthelper.authentication.domain.result.ValidationResult

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {
        if (password.length < 8)
            return ValidationResult(errorMessage = "The password must consist of 8 letters at least!!")
        val containsLetters = password.any { it.isLetter() }
        val containsDigits = password.any { it.isDigit() }
        if (!(containsLetters && containsDigits))
            return ValidationResult(errorMessage = "The password must contain at least one letter and at least one digit!!")
        return ValidationResult(validatedData = password)
    }
}