package com.example.itthelper.authentication.domain.validation

interface EmailValidator {
    fun isValidEmail(email: String): Boolean
}