package com.example.itthelper.authentication.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.authentication.domain.result.AuthResult
import com.example.itthelper.authentication.domain.usecase.authentication.AuthenticateUseCase
import com.example.itthelper.authentication.domain.usecase.preferences.ReadWelcomeDoneStatusUseCase
import com.example.itthelper.authentication.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    readWelcomeDoneStatusUseCase: ReadWelcomeDoneStatusUseCase,
    authenticateUseCase: AuthenticateUseCase
) : ViewModel() {
    private val _startDestination = mutableStateOf<String?>(
        null
    )
    val startDestination: State<String?>
        get() = _startDestination
    private val authResultChannel = Channel<AuthResult<Unit>>()
    val authResults = authResultChannel.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val authResult = authenticateUseCase()
            val isWelcomeDoneBefore = readWelcomeDoneStatusUseCase()
            withContext(Dispatchers.Main) {
                authResultChannel.send(authResult)
                if (isWelcomeDoneBefore) {
                    _startDestination.value = Screen.AUTH.route
                } else {
                    _startDestination.value = Screen.WELCOME.route
                }
            }
        }
    }

}