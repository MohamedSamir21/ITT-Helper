package com.example.itthelper.career_guidance_hub.presentation.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.components.BackButton
import com.example.itthelper.career_guidance_hub.presentation.components.EmailTextField
import com.example.itthelper.career_guidance_hub.presentation.components.MessageTextField
import com.example.itthelper.career_guidance_hub.presentation.components.NameTextField
import com.example.itthelper.career_guidance_hub.presentation.components.PhoneTextField
import com.example.itthelper.career_guidance_hub.presentation.components.SendButton
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun ContactScreen(
    navController: NavController,
    contactViewModel: ContactViewModel
) {
    val viewModel: ContactViewModel = remember {
        contactViewModel
    }

    ContactContent(
        navController = navController,
        state = viewModel.state.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ContactContent(
    navController: NavController,
    state: ContactScreenState,
    onEvent: (ContactScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ContactHeader(navController)
        HorizontalDivider(modifier = Modifier.padding(20.dp))
        ContactMessage()
        ContactForm(state) { onEvent(it) }
    }
}

@Composable
private fun ContactHeader(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton(navController = navController)
        ContactText(modifier = Modifier.padding(2.dp))
    }
}

@Composable
private fun ContactMessage() {
    Text(
        modifier = Modifier.padding(20.dp),
        text = stringResource(R.string.contact_message),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun ContactForm(
    state: ContactScreenState,
    onEvent: (ContactScreenEvent) -> Unit
) {
    Column {
        NameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            name = state.name
        ) { newName ->
            onEvent(
                ContactScreenEvent.Name(
                    value = newName
                )
            )
        }
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            email = state.email
        ) { newEmail ->
            onEvent(
                ContactScreenEvent.Email(
                    value = newEmail
                )
            )
        }
        PhoneTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            phone = state.phone
        ) { newPhone ->
            onEvent(
                ContactScreenEvent.Phone(
                    value = newPhone
                )
            )
        }
        SubjectTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            subject = state.subject
        ) { newSubject ->
            onEvent(
                ContactScreenEvent.Subject(
                    value = newSubject
                )
            )
        }
        MessageTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            message = state.message
        ) { newMessage ->
            onEvent(
                ContactScreenEvent.Message(
                    value = newMessage
                )
            )
        }
        SendButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            onEvent(
                ContactScreenEvent.Send
            )
        }
    }
}

@Composable
private fun ContactText(
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.contact_with_us),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun SubjectTextField(
    modifier: Modifier = Modifier,
    subject: String,
    onSubjectChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = subject,
        label = {
            Text(text = stringResource(R.string.subject))
        },
        singleLine = true,
        onValueChange = { newSubject ->
            onSubjectChange(newSubject)
        }
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun ContactContentPreview() {
    val state = ContactScreenState()

    ITTHelperTheme {
        ContactContent(
            navController = rememberNavController(),
            state = state,
            onEvent = {}
        )
    }
}