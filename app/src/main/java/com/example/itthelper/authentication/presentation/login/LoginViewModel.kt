package com.example.itthelper.authentication.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.authentication.domain.model.LoginUserData
import com.example.itthelper.authentication.domain.result.AuthResult
import com.example.itthelper.authentication.domain.usecase.authentication.LogInUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {
    private val _state = mutableStateOf(
        LoginScreenState(
            userData = LoginUserData("", "")
        )
    )
    val state: State<LoginScreenState>
        get() = _state
    private val authResultChannel = Channel<AuthResult<Unit>>()
    val authResults = authResultChannel.receiveAsFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.UserDataChanged -> {
                _state.value = state.value.copy(
                    userData = event.userData.copy(
                        email = event.userData.email,
                        password = event.userData.password
                    )
                )
            }

            is LoginScreenEvent.Submit -> {
                viewModelScope.launch {
                    submitData()
                }
            }
        }
    }

    private suspend fun submitData() {
        val state = state.value

        val emailValidation = validateEmailUseCase(state.userData.email)
        emailValidation.errorMessage?.let {
            _state.value = state.copy(
                emailError = emailValidation.errorMessage
            )
            return
        }
        withContext(Dispatchers.IO) {
            val loginResult = logInUseCase(
                userData = LoginUserData(
                    email = emailValidation.validatedData.toString(),
                    password = state.userData.password
                )
            )
            withContext(Dispatchers.Main) {
                authResultChannel.send(loginResult)
            }
        }
    }
}