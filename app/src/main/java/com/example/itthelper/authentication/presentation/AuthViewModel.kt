package com.example.itthelper.authentication.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.authentication.domain.usecase.preferences.ReadLoginDoneStatusUseCase
import com.example.itthelper.authentication.domain.usecase.preferences.ReadWelcomeDoneStatusUseCase
import com.example.itthelper.authentication.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val currentLoginStatus = readLoginDoneStatusUseCase()
            val isWelcomeDoneBefore = readWelcomeDoneStatusUseCase()
            withContext(Dispatchers.Main) {
                _loginStatus.value = currentLoginStatus
                if (isWelcomeDoneBefore) {
                    _startDestination.value = Screen.AUTH.route
                } else {
                    _startDestination.value = Screen.WELCOME.route
                }
                _isLoading.value = false
            }
        }
    }

}