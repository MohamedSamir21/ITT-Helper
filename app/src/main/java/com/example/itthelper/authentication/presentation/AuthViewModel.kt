package com.example.itthelper.authentication.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.authentication.domain.usecase.preferences.ReadLoginDoneStatusUseCase
import com.example.itthelper.authentication.domain.usecase.preferences.ReadWelcomeDoneStatusUseCase
import com.example.itthelper.authentication.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    readWelcomeDoneStatusUseCase: ReadWelcomeDoneStatusUseCase,
    readLoginDoneStatusUseCase: ReadLoginDoneStatusUseCase
) : ViewModel() {
    private val _loginStatus = mutableStateOf(false)
    val loginStatus: State<Boolean>
        get() = _loginStatus
    private val _startDestination = mutableStateOf(
        Screen.WELCOME.route
    )
    val startDestination: State<String>
        get() = _startDestination
    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean>
        get() = _isLoading

    init {
        viewModelScope.launch {
            val loginStatusJob = launch {
                _loginStatus.value = readLoginDoneStatusUseCase()
            }
            val welcomeDoneJob = launch {
                _startDestination.value =
                    if (readWelcomeDoneStatusUseCase()) Screen.AUTH.route else Screen.WELCOME.route
            }
            loginStatusJob.join()
            welcomeDoneJob.join()

            delay(500L)
            _isLoading.value = false
        }
    }
}

