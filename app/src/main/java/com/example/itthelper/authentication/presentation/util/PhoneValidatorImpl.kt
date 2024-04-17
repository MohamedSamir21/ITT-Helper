package com.example.itthelper.authentication.presentation.util

import android.util.Patterns
import com.example.itthelper.authentication.domain.validation.PhoneValidator

class PhoneValidatorImpl : PhoneValidator {
    override fun isValid(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }
}