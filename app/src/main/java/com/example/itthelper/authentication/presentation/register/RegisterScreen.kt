package com.example.itthelper.authentication.presentation.register

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
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
import com.example.itthelper.authentication.presentation.components.UsernameTextField
import com.example.itthelper.authentication.presentation.components.RegisterButton
import com.example.itthelper.authentication.presentation.navigation.Screen


@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    Toast.makeText(
                        context,
                        "New account has been created!!",
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
            text = stringResource(R.string.lets_register),
            fontSize = 30.sp
        )
        AuthMethodSwitch(
            navController = navController,
            route = Screen.LOGIN.route,
            helperMessage = stringResource(id = R.string.do_you_have_an_account),
            authMessage = stringResource(id = R.string.log_in)
        )
        FormTextFields(viewModel = viewModel)
        AgreementSection(
            checked = state.userData.acceptedTerms,
            termsErrorMessage = state.acceptedTermsError
        ) {
            viewModel.onEvent(
                RegisterScreenEvent.UserDataChanged(
                    userData = state.userData.copy(acceptedTerms = it)
                )
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            RegisterButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                viewModel.onEvent(RegisterScreenEvent.Submit)
            }
        }
    }
}

@Composable
fun FormTextFields(
    viewModel: RegisterViewModel
) {
    val state = viewModel.state.value
    UsernameTextField(
        value = state.userData.username,
        isError = state.usernameError != null,
        errorMessage = state.usernameError.toString(),
        disableSupportingText = false,
    ) { newUserName ->
        Log.i("RegisterScreen", newUserName)
        viewModel.onEvent(
            RegisterScreenEvent.UserDataChanged(
                userData = state.userData.copy(username = newUserName)
            )
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    EmailTextField(
        value = state.userData.email,
        isError = state.emailError != null,
        errorMessage = state.emailError.toString(),
        disableSupportingText = false,
        onValueChange = { newEmail ->
            viewModel.onEvent(
                RegisterScreenEvent.UserDataChanged(
                    userData = state.userData.copy(
                        email = newEmail
                    )
                )
            )
        }
    )
    Spacer(modifier = Modifier.height(5.dp))
    PasswordTextField(
        value = state.userData.password,
        disableSupportingText = false,
        onValueChange = { newPassword ->
            viewModel.onEvent(
                RegisterScreenEvent.UserDataChanged(
                    userData = state.userData.copy(
                        password = newPassword
                    )
                )
            )
        },
        label = stringResource(id = R.string.password),
        isError = state.passwordError != null,
        errorMessage = state.passwordError.toString()
    )
    Spacer(modifier = Modifier.height(5.dp))
    PasswordTextField(
        value = state.userData.confirmedPassword,
        disableSupportingText = false,
        onValueChange = { newConfirmedPassword ->
            viewModel.onEvent(
                RegisterScreenEvent.UserDataChanged(
                    userData = state.userData.copy(
                        confirmedPassword = newConfirmedPassword
                    )
                )
            )
        },
        label = stringResource(id = R.string.confirm_password),
        isError = state.confirmedPasswordError != null,
        errorMessage = state.confirmedPasswordError.toString()
    )
    Spacer(modifier = Modifier.height(5.dp))
    PhoneNumberTextField(
        value = state.userData.phoneNumber,
        isError = state.phoneNumberError != null,
        errorMessage = state.phoneNumberError.toString(),
    ) { newPhoneNumber ->
        viewModel.onEvent(
            RegisterScreenEvent.UserDataChanged(
                userData = state.userData.copy(phoneNumber = newPhoneNumber)
            )
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
private fun AgreementSection(
    checked: Boolean,
    termsErrorMessage: String? = null,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) }
            )
            AgreementText(
                fontSize = 12.sp,
                textAlign = TextAlign.Start
            )
        }
        termsErrorMessage?.let {
            Text(
                text = termsErrorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 40.dp)
            )
        }
    }
}

@Composable
private fun AgreementText(
    fontSize: TextUnit,
    textAlign: TextAlign
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.registering_commitment) + " ")
            pushStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            append(stringResource(id = R.string.the_terms))
            pop()
            append(", ")
            pushStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            append(stringResource(id = R.string.conditions) + " ")
            pop()
            append(stringResource(id = R.string.and) + " ")
            pushStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            append(stringResource(id = R.string.privacy_policy))
            pop()
            append(".")
        },
        textAlign = textAlign,
        fontSize = fontSize,
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    errorMessage: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        label = {
            Text(text = stringResource(id = R.string.phone_number))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = stringResource(id = R.string.phone_number)
            )
        },
        supportingText = {
            if (isError)
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            else
                Text(text = "*" + stringResource(id = R.string.required))
        },
        onValueChange = { newPhoneNumber ->
            onValueChange(newPhoneNumber)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        ),
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

