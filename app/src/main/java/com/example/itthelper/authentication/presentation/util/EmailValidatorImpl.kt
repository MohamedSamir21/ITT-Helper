package com.example.itthelper.authentication.presentation.util

import android.util.Patterns
import com.example.itthelper.authentication.domain.validation.EmailValidator

class EmailValidatorImpl: EmailValidator {
    override fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}