package com.example.itthelper.authentication.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.itthelper.R
import com.example.itthelper.authentication.presentation.components.AppLogo
import com.example.itthelper.authentication.presentation.components.EmailTextField
import com.example.itthelper.authentication.presentation.components.PasswordTextField
import com.example.itthelper.authentication.presentation.components.RegisterButton
import com.example.itthelper.authentication.presentation.navigation.Screen


@Composable
fun RegisterScreen(
    navController: NavController
) {
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
        HavingAccount(navController)
        PersonTextField()
        Spacer(modifier = Modifier.height(10.dp))
        EmailTextField(value = "", onValueChange = {})
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = "",
            onValueChange = {},
            label = stringResource(id = R.string.password)
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = "",
            onValueChange = {},
            label = stringResource(id = R.string.confirm_password)
        )
        AgreementSection()
        RegisterButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}

@Composable
private fun HavingAccount(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.do_you_have_an_account),
            fontSize = 15.sp
        )
        TextButton(
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.LOGIN.route)
            }
        ) {
            Text(text = stringResource(R.string.log_in))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonTextField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(text = stringResource(R.string.nick_name))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(R.string.person)
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AgreementSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = {}
        )
        AgreementText(
            fontSize = 12.sp,
            textAlign = TextAlign.Start
        )
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

