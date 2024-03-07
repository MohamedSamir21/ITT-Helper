package com.example.itthelper.authentication.presentation.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itthelper.R
import com.example.itthelper.authentication.presentation.navigation.Screen

@Composable
fun AuthScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.6f),
            contentAlignment = Alignment.BottomCenter
        ) {
            WelcomeSection()
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.4f),
            contentAlignment = Alignment.Center
        ) {
            AuthSection(navController)
        }
    }
}


@Composable
fun WelcomeSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo(fontSize = 50.sp)
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
        )
        Text(
            text = stringResource(R.string.register_request_message),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

@Composable
fun AuthSection(
    navController: NavHostController
) {
    Column {
        RegisterButton(modifier = Modifier.padding(horizontal = 10.dp)){
            navController.navigate(Screen.REGISTER.route)
        }
        LoginButton(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)){
            navController.navigate(Screen.LOGIN.route)
        }
        AgreementText(fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    fontSize: TextUnit
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append("ITT")
            pushStyle(
                SpanStyle(
                    color = Color.Green
                )
            )
            append("Helper")
        },
        fontSize = fontSize
    )
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    FilledTonalButton(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .padding()
    ) {
        Text(
            text = stringResource(R.string.login)
        )
    }
}

@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .padding()
    ) {
        Text(
            text = stringResource(R.string.create_account)
        )
    }
}

@Composable
fun AgreementText(
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    textAlign: TextAlign
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.logging_registering) + " ")
            pushStyle(
                SpanStyle(
                    color = Color.Green
                )
            )
            append(stringResource(id = R.string.the_terms))
            pop()
            append(", ")
            pushStyle(
                SpanStyle(
                    color = Color.Green
                )
            )
            append(stringResource(id = R.string.conditions) + " ")
            pop()
            append(stringResource(id = R.string.and) + " ")
            pushStyle(
                SpanStyle(
                    color = Color.Green
                )
            )
            append(stringResource(id = R.string.privacy_policy))
            pop()
            append(".")
        },
        textAlign = textAlign,
        fontSize = fontSize,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(
    showBackground = true
)
@Composable
fun WelcomeSectionPreview() {
    WelcomeSection()
}