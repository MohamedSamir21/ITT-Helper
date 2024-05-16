package com.example.itthelper.career_guidance_hub.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.domain.usecase.LogoutUserUseCase
import com.example.itthelper.career_guidance_hub.presentation.util.UiText
import com.example.itthelper.career_guidance_hub.presentation.util.asUiText
import com.example.itthelper.core.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {
    private val logoutChannel = Channel<ProfileScreenEvent.Logout>()
    val logoutResult = logoutChannel.receiveAsFlow()
    private val _isLoggingOut = mutableStateOf(false)
    val isLoggingOut: State<Boolean>
        get() = _isLoggingOut

    fun onLogout() {
        viewModelScope.launch {
            _isLoggingOut.value = true
            when (val result = logoutUserUseCase()) {
                is Result.Success -> {
                    logoutChannel.send(
                        ProfileScreenEvent.Logout.Success(
                            message = UiText.StringResource(
                                R.string.logout_success_message
                            )
                        )
                    )
                    _isLoggingOut.value = false
                }

                is Result.Failure -> {
                    logoutChannel.send(
                        ProfileScreenEvent.Logout.Error(
                            message = result.error.asUiText()
                        )
                    )
                    _isLoggingOut.value = false
                }
            }
        }
    }
}