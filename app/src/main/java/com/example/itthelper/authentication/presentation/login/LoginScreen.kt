package com.example.itthelper.authentication.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.itthelper.career_guidance_hub.presentation.CareerHubActivity
import com.example.itthelper.R
import com.example.itthelper.authentication.domain.result.AuthResult
import com.example.itthelper.authentication.presentation.components.AppLogo
import com.example.itthelper.authentication.presentation.components.AuthMethodSwitch
import com.example.itthelper.authentication.presentation.components.EmailTextField
import com.example.itthelper.authentication.presentation.components.PasswordTextField
import com.example.itthelper.authentication.presentation.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {

    val state = viewModel.state.value
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    Toast.makeText(
                        context,
                        "Logged in successfully!!",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.popBackStack()
                    Intent(context, CareerHubActivity::class.java).let {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(it)
                    }
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "We cannot authorize you!!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error has occurred!!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo(
            modifier = Modifier.padding(vertical = 20.dp),
            fontSize = 25.sp
        )
        Text(
            text = stringResource(R.string.lets_login),
            fontSize = 30.sp
        )
        AuthMethodSwitch(
            navController = navController,
            route = Screen.REGISTER.route,
            helperMessage = stringResource(R.string.havent_account_yet),
            authMessage = stringResource(R.string.register)
        )
        EmailTextField(
            value = state.userData.email,
            onValueChange = {
                viewModel.onEvent(
                    LoginScreenEvent.UserDataChanged(
                        userData = state.userData.copy(email = it)
                    )
                )
            },
            isError = state.emailError != null,
            errorMessage = state.emailError.toString()
        )
        PasswordTextField(
            value = state.userData.password,
            onValueChange = {
                viewModel.onEvent(
                    LoginScreenEvent.UserDataChanged(
                        userData = state.userData.copy(password = it)
                    )
                )
            },
            label = "Password",
            isError = false,
            errorMessage = ""
        )
        Button(
            onClick = {
                viewModel.onEvent(LoginScreenEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

        ) {
            Text(text = "Login")
        }
    }
}