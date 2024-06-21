package com.example.itthelper.career_guidance_hub.presentation.chat_bot

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.components.BackButton
import kotlinx.coroutines.delay

@Composable
fun ChatBotScreen(
    navController: NavController,
    chatBotViewModel: ChatBotViewModel
) {
    val viewModel: ChatBotViewModel = remember {
        chatBotViewModel
    }

    ChatBotContent(
        navController = navController,
        state = viewModel.state.value,
        onChatTextChange = {
            viewModel.onEvent(ChatBotScreenEvent.ClientMessage(it))
        },
        onSendClicked = {
            viewModel.onEvent(ChatBotScreenEvent.Send)
        }
    )
}

@Composable
fun ChatBotContent(
    navController: NavController,
    state: ChatBotScreenState,
    onChatTextChange: (String) -> Unit,
    onSendClicked: (ChatBotScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ChatHeader(navController)
        ChatContent(
            modifier = Modifier.weight(1f),
            messages = state.messages,
            isReplaying = state.isReplaying
        )
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            ChatTextField(
                modifier = Modifier.weight(1f),
                clientMessage = state.clientMessage,
                onValueChange = {
                    onChatTextChange(it)
                }
            )
            IconButton(
                onClick = {
                    onSendClicked(ChatBotScreenEvent.Send)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = stringResource(
                        id = R.string.send
                    )
                )
            }
        }
    }
}

@Composable
private fun ChatTextField(
    modifier: Modifier = Modifier,
    clientMessage: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = clientMessage,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(text = "Type your message here...")
        },
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
private fun ChatHeader(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton(navController = navController)
        Text(
            modifier = Modifier,
            text = stringResource(R.string.chat_service),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun ChatContent(
    modifier: Modifier = Modifier,
    messages: List<Map<String, String>>,
    isReplaying: Boolean
) {
    LazyColumn(
        modifier = modifier
            .padding(10.dp)
    ) {
        messages.forEach { message ->
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (message.containsKey("bot")) {
                        BotIconWithMessage(message)
                    } else {
                        message["client"]?.let {
                            ClientMessage(it)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        item {
            if (isReplaying) {
                BotIconWithPulsingAnimation()
            }
        }
    }
}

@Composable
private fun BotIconWithPulsingAnimation() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BotIcon()
        Spacer(modifier = Modifier.width(10.dp))
        BotReplayingAnimation()
    }
}

@Composable
private fun ClientMessage(it: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.End,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(10.dp)
        )
    }
}

@Composable
private fun BotIconWithMessage(message: Map<String, String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BotIcon()
        Spacer(modifier = Modifier.width(10.dp))
        message["bot"]?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(10.dp)
            )
        }
    }
}

@Composable
private fun BotIcon() {
    Icon(
        painter = painterResource(id = R.drawable.logo_12_plus),
        contentDescription = "",
        tint = MaterialTheme.colorScheme.primary.copy(
            alpha = .8f
        ),
        modifier = Modifier.size(30.dp)
    )
}

@Composable
fun BotReplayingAnimation(
    modifier: Modifier = Modifier
) {
    // Animation states for each dot
    var dot1Visible by remember { mutableStateOf(false) }
    var dot2Visible by remember { mutableStateOf(false) }
    var dot3Visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            dot1Visible = true
            delay(300)
            dot2Visible = true
            delay(300)
            dot3Visible = true
            delay(300)
            dot1Visible = false
            dot2Visible = false
            dot3Visible = false
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Dot(isVisible = dot1Visible)
        Spacer(modifier = Modifier.width(4.dp))
        Dot(isVisible = dot2Visible)
        Spacer(modifier = Modifier.width(4.dp))
        Dot(isVisible = dot3Visible)
    }
}

@Composable
fun Dot(isVisible: Boolean) {
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = alpha),
                shape = CircleShape
            )
    )
}