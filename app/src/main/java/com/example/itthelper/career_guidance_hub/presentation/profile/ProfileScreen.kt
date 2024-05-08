package com.example.itthelper.career_guidance_hub.presentation.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ContactSupport
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.components.BackButton
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileHeader(navController)
        HorizontalDivider(modifier = Modifier.padding(20.dp))
        ProfileInfo()
        Spacer(modifier = Modifier.padding(20.dp))
        ContactUsButton(navController = navController)
        FeedbackButton(navController = navController)
        LogoutButton {}
    }
}

@Composable
private fun ProfileHeader(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton(navController = navController)
        ProfileText(modifier = Modifier.padding(start = 2.dp))
    }
}

@Composable
private fun ProfileInfo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = stringResource(R.string.profile_image),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(100.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = "Username",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = "Username@gamil.com",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ContactUsButton(
    navController: NavController
) {
    TextButton(
        modifier = Modifier.padding(start = 5.dp),
        onClick = {
            navController.navigate(Screen.ContactUs.route)
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ContactSupport,
            contentDescription = stringResource(R.string.contact_us)
        )
        Spacer(modifier = Modifier.padding(end = 16.dp))
        Text(
            text = stringResource(id = R.string.contact_us),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun FeedbackButton(
    navController: NavController
) {
    TextButton(
        modifier = Modifier.padding(start = 5.dp),
        onClick = {
            navController.navigate(Screen.Feedback.route)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Feedback,
            contentDescription = stringResource(R.string.feedback)
        )
        Spacer(modifier = Modifier.padding(end = 16.dp))
        Text(
            text = stringResource(R.string.feedback),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun LogoutButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            shape = RectangleShape
        ) {
            Text(text = stringResource(R.string.logout))
        }
    }
}

@Composable
private fun ProfileText(
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.profile),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Preview
@Composable
private fun ProfileInfoPreview() {
    ITTHelperTheme {
        ProfileInfo()
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ProfileScreenPreview() {
    ITTHelperTheme {
        ProfileScreen(navController = rememberNavController())
    }
}