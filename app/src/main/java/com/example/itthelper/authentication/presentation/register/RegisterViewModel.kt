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
import com.example.itthelper.authentication.domain.usecase.validation.ValidateTermsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateConfirmedPasswordUseCase: ValidateConfirmedPasswordUseCase,
    private val validatedTermsUseCase: ValidateTermsUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private var _state = mutableStateOf(
        RegisterScreenState(
            userData = RegisterUserData("", "", "", "", false)
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
                        nickName = event.userData.nickName,
                        email = event.userData.email,
                        password = event.userData.password,
                        confirmedPassword = event.userData.confirmedPassword,
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
        val registerResult = registerUseCase(
            RegisterUserData(
                nickName = state.userData.nickName,
                email = state.userData.email,
                password = state.userData.password,
                confirmedPassword = state.userData.confirmedPassword,
                acceptedTerms = state.userData.acceptedTerms
            )
        )
        authResultChannel.send(registerResult)
    }

    private fun hasInvalidInputData(): Boolean {
        val state = state.value

        // Check the validation of user data.
        val emailValidation = validateEmailUseCase(state.userData.email)
        val passwordValidation = validatePasswordUseCase(state.userData.password)
        val confirmedPasswordValidation = validateConfirmedPasswordUseCase(
            state.userData.password,
            state.userData.confirmedPassword
        )
        val acceptedTermsValidation = validatedTermsUseCase(state.userData.acceptedTerms)

        val validationResults = listOf(
            emailValidation,
            passwordValidation,
            confirmedPasswordValidation,
            acceptedTermsValidation
        )
        val hasError = validationResults.any { it.errorMessage != null }

        if (hasError) {
            _state.value = state.copy(
                emailError = emailValidation.errorMessage,
                passwordError = passwordValidation.errorMessage,
                confirmedPasswordError = confirmedPasswordValidation.errorMessage,
                acceptedTermsError = acceptedTermsValidation.errorMessage
            )
        }
        return hasError
    }

}