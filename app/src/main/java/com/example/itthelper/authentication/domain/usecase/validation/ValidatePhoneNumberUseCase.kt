package com.example.itthelper.authentication.domain.usecase.validation

import com.example.itthelper.authentication.domain.result.ValidationResult
import com.example.itthelper.authentication.domain.validation.PhoneValidator
import javax.inject.Inject

class ValidatePhoneNumberUseCase @Inject constructor(
    private val phoneValidator: PhoneValidator
) {

    operator fun invoke(phone: String): ValidationResult {
        return if (phoneValidator.isValid(phone)){
            ValidationResult(validatedData = phone)
        } else {
            ValidationResult(errorMessage = "This is not a valid phone number!!")
        }
    }
}