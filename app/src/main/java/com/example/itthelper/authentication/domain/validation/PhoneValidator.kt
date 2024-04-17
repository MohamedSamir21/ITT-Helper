package com.example.itthelper.authentication.domain.validation

interface PhoneValidator {
    fun isValid(phone: String): Boolean
}