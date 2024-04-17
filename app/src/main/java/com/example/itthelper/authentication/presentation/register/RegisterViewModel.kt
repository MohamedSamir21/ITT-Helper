package com.example.itthelper.authentication.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.authentication.domain.model.RegisterUserData
import com.example.itthelper.authentication.domain.result.AuthResult
import com.example.itthelper.authentication.domain.usecase.authentication.RegisterUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidateConfirmedPasswordUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidateEmailUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidatePasswordUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidatePhoneNumberUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidateTermsUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidateUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateConfirmedPasswordUseCase: ValidateConfirmedPasswordUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validateTermsUseCase: ValidateTermsUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private var _state = mutableStateOf(
        RegisterScreenState(
            userData = RegisterUserData("", "", "", "", "", false)
        )
    )
    val state: State<RegisterScreenState>
        get() = _state
    private val authResultChannel = Channel<AuthResult<Unit>>()
    val authResults = authResultChannel.receiveAsFlow()

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.UserDataChanged -> {
                _state.value = _state.value.copy(
                    userData = event.userData.copy(
                        username = event.userData.username,
                        email = event.userData.email,
                        password = event.userData.password,
                        confirmedPassword = event.userData.confirmedPassword,
                        phoneNumber = event.userData.phoneNumber,
                        acceptedTerms = event.userData.acceptedTerms
                    )
                )
            }

            is RegisterScreenEvent.Submit -> {
                viewModelScope.launch(Dispatchers.IO) {
                    submitData()
                }
            }
        }
    }

    private suspend fun submitData() {
        if (hasInvalidInputData()) return

        val state = state.value
        _state.value = state.copy(isLoading = true)
        val registerResult = registerUseCase(
            RegisterUserData(
                username = state.userData.username,
                email = state.userData.email,
                password = state.userData.password,
                confirmedPassword = state.userData.confirmedPassword,
                phoneNumber = state.userData.phoneNumber,
                acceptedTerms = state.userData.acceptedTerms
            )
        )
        authResultChannel.send(registerResult)
        _state.value = state.copy(isLoading = false)
    }

    private fun hasInvalidInputData(): Boolean {
        val state = state.value

        // Check the validation of user data.
        val usernameValidation = validateUsername(state.userData.username)
        val emailValidation = validateEmailUseCase(state.userData.email)
        val passwordValidation = validatePasswordUseCase(state.userData.password)
        val confirmedPasswordValidation = validateConfirmedPasswordUseCase(
            state.userData.password,
            state.userData.confirmedPassword
        )
        val phoneValidation = validatePhoneNumberUseCase(state.userData.phoneNumber)
        val acceptedTermsValidation = validateTermsUseCase(state.userData.acceptedTerms)

        val validationResults = listOf(
            usernameValidation,
            emailValidation,
            passwordValidation,
            confirmedPasswordValidation,
            phoneValidation,
            acceptedTermsValidation
        )
        val hasError = validationResults.any { it.errorMessage != null }

        if (hasError) {
            _state.value = state.copy(
                usernameError = usernameValidation.errorMessage,
                emailError = emailValidation.errorMessage,
                passwordError = passwordValidation.errorMessage,
                confirmedPasswordError = confirmedPasswordValidation.errorMessage,
                phoneNumberError = phoneValidation.errorMessage,
                acceptedTermsError = acceptedTermsValidation.errorMessage
            )
        }
        return hasError
    }

}