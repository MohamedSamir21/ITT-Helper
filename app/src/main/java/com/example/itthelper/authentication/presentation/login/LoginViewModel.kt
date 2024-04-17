package com.example.itthelper.authentication.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.authentication.domain.model.LoginUserData
import com.example.itthelper.authentication.domain.result.AuthResult
import com.example.itthelper.authentication.domain.usecase.authentication.LogInUseCase
import com.example.itthelper.authentication.domain.usecase.validation.ValidateUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
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
                        username = event.userData.username,
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

        val usernameValidation = validateUsername(state.userData.username)
        usernameValidation.errorMessage?.let {
            _state.value = state.copy(
                usernameError = usernameValidation.errorMessage
            )
            return
        }
        _state.value = state.copy(isLoading = true)
        withContext(Dispatchers.IO) {
            val loginResult = logInUseCase(
                userData = LoginUserData(
                    username = usernameValidation.validatedData.toString(),
                    password = state.userData.password
                )
            )
            withContext(Dispatchers.Main) {
                authResultChannel.send(loginResult)
            }
        }
        _state.value = state.copy(isLoading = false)
    }
}