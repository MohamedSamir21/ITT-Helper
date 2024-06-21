package com.example.itthelper.career_guidance_hub.presentation.feedback

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
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
import com.example.itthelper.career_guidance_hub.presentation.components.ResultDialog
import com.example.itthelper.career_guidance_hub.presentation.components.SendButton
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.career_guidance_hub.presentation.util.DateUtil
import com.example.itthelper.career_guidance_hub.presentation.util.UiText
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun FeedbackScreen(
    navController: NavController,
    feedbackViewModel: FeedbackViewModel,
    onUnauthorized: (UiText) -> Unit
) {
    val viewModel: FeedbackViewModel = remember {
        feedbackViewModel
    }
    viewModel.unauthorizedResult.collectAsState(initial = null).value?.let {
        onUnauthorized(it.message)
    }
    FeedbackContent(
        navController = navController,
        state = viewModel.state.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun FeedbackContent(
    navController: NavController,
    state: FeedbackScreenState,
    onEvent: (FeedbackScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        FeedbackHeader(navController)
        HorizontalDivider(modifier = Modifier.padding(20.dp))
        FeedbackMessage()
        FeedbackForm(state, onEvent)

        if (state.showResultDialog) {
            val messageTitle = state.dialogTitle?.asString()
            val messageBody = state.dialogBody.asString()
            messageTitle?.let {
                ResultDialog(
                    messageTitle = it,
                    messageBody = messageBody,
                    isSuccessful = it == stringResource(id = R.string.success),
                    contentDescription = messageBody,
                    onConfirmedClicked = {
                        navController.popBackStack(Screen.Home.route, false)
                        onEvent(FeedbackScreenEvent.HideResultDialog)
                    },
                    onDismissedClicked = {
                        onEvent(FeedbackScreenEvent.HideResultDialog)
                    }
                ) {
                    onEvent(FeedbackScreenEvent.HideResultDialog)
                }
            }
        }
    }
}

@Composable
fun FeedbackHeader(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton(navController = navController)
        FeedbackText(Modifier.padding(2.dp))
    }
}

@Composable
private fun FeedbackMessage() {
    Text(
        modifier = Modifier.padding(20.dp),
        text = stringResource(R.string.contact_message),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun FeedbackForm(
    state: FeedbackScreenState,
    onEvent: (FeedbackScreenEvent) -> Unit
) {
    Column {
        NameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            name = state.name
        ) { newName ->
            onEvent(
                FeedbackScreenEvent.Name(
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
                FeedbackScreenEvent.Email(
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
                FeedbackScreenEvent.Phone(
                    value = newPhone
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PositionDropdownMenuTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp),
                state = state
            ) {
                onEvent(it)
            }
            TypeDropdownMenuTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp),
                state = state
            ) {
                onEvent(it)
            }
        }
        DatePickerTextField(state = state) {
            onEvent(it)
        }
        MessageTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            message = state.message
        ) { newMessage ->
            onEvent(
                FeedbackScreenEvent.Message(
                    value = newMessage
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ResetButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp)
            ) {
                onEvent(
                    FeedbackScreenEvent.Reset
                )
            }
            if (state.isSending) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                SendButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp)
                ) {
                    onEvent(FeedbackScreenEvent.Send)
                }
            }

        }
    }
}

@Composable
private fun FeedbackText(
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.feedback),
        style = MaterialTheme.typography.headlineSmall
    )
}


@Composable
private fun PositionDropdownMenuTextField(
    modifier: Modifier = Modifier,
    state: FeedbackScreenState,
    onEvent: (FeedbackScreenEvent) -> Unit
) {
    Box(modifier = modifier) {
        PositionTextField(
            position = state.positionDropdownMenuState.selectedPosition,
            showPositionDropdownMenu = state.positionDropdownMenuState.showPositionDropdownMenu,
            onTogglePositionDropdownMenu = {
                onEvent(
                    FeedbackScreenEvent.TogglePositionDropdownMenu
                )
            }
        ) { newPosition ->
            onEvent(
                FeedbackScreenEvent.Position(
                    value = newPosition
                )
            )
        }
        CustomDropdownMenu(
            items = state.positionDropdownMenuState.items,
            isExpanded = state.positionDropdownMenuState.showPositionDropdownMenu,
            onDismiss = {
                onEvent(
                    FeedbackScreenEvent.TogglePositionDropdownMenu
                )
            }
        ) { newPosition ->
            onEvent(
                FeedbackScreenEvent.Position(
                    value = newPosition
                )
            )
            onEvent(
                FeedbackScreenEvent.TogglePositionDropdownMenu
            )
        }
    }
}

@Composable
private fun TypeDropdownMenuTextField(
    modifier: Modifier = Modifier,
    state: FeedbackScreenState,
    onEvent: (FeedbackScreenEvent) -> Unit
) {
    Box(modifier = modifier) {
        TypeTextField(
            type = state.typeDropdownMenuState.selectedType,
            showTypeDropdownMenu = state.typeDropdownMenuState.showTypeDropdownMenu,
            onToggleTypeDropdownMenu = {
                onEvent(
                    FeedbackScreenEvent.ToggleTypeDropdownMenu
                )
            }
        ) { newType ->
            onEvent(
                FeedbackScreenEvent.Type(
                    value = newType
                )
            )
        }
        CustomDropdownMenu(
            items = state.typeDropdownMenuState.items,
            isExpanded = state.typeDropdownMenuState.showTypeDropdownMenu,
            onDismiss = {
                onEvent(
                    FeedbackScreenEvent.ToggleTypeDropdownMenu
                )
            }
        ) { newType ->
            onEvent(
                FeedbackScreenEvent.Type(
                    value = newType
                )
            )
            onEvent(
                FeedbackScreenEvent.ToggleTypeDropdownMenu
            )
        }
    }
}

@Composable
private fun DatePickerTextField(
    state: FeedbackScreenState,
    onEvent: (FeedbackScreenEvent) -> Unit
) {
    DateTextField(
        date = state.datePickerState.selectedDate,
        showDatePicker = state.datePickerState.showDatePicker,
        onToggleDatePicker = {
            onEvent(
                FeedbackScreenEvent.ToggleDatePicker
            )
        }
    ) { newDate ->
        onEvent(
            FeedbackScreenEvent.Date(
                value = newDate
            )
        )
    }
    if (state.datePickerState.showDatePicker) {
        CustomDatePickerDialog {
            onEvent(it)
        }
    }
}

@Composable
private fun PositionTextField(
    position: String,
    showPositionDropdownMenu: Boolean,
    onTogglePositionDropdownMenu: () -> Unit,
    onPositionChange: (String) -> Unit
) {
    TextField(
        value = position,
        onValueChange = { newPosition ->
            onPositionChange(newPosition)
        },
        label = {
            Text(text = stringResource(R.string.position))
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onTogglePositionDropdownMenu()
                },
                imageVector = if (showPositionDropdownMenu)
                    Icons.Default.ExpandMore
                else
                    Icons.Default.ExpandLess,
                tint = if (showPositionDropdownMenu)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(id = R.string.position)
            )
        },
        singleLine = true
    )
}

@Composable
private fun TypeTextField(
    type: String,
    showTypeDropdownMenu: Boolean,
    onToggleTypeDropdownMenu: () -> Unit,
    onTypeChange: (String) -> Unit
) {
    TextField(
        value = type,
        onValueChange = { newPosition ->
            onTypeChange(newPosition)
        },
        label = {
            Text(text = stringResource(R.string.type))
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onToggleTypeDropdownMenu()
                },
                imageVector = if (showTypeDropdownMenu)
                    Icons.Default.ExpandMore
                else
                    Icons.Default.ExpandLess,
                tint = if (showTypeDropdownMenu)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.type)
            )
        },
        singleLine = true
    )
}

@Composable
private fun DateTextField(
    date: String,
    showDatePicker: Boolean,
    onToggleDatePicker: () -> Unit,
    onDateChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        value = date,
        onValueChange = { newDate ->
            onDateChange(newDate)
        },
        label = {
            Text(text = stringResource(R.string.date))
        },
        singleLine = true,
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onToggleDatePicker()
                },
                imageVector = Icons.Default.CalendarMonth,
                tint = if (showDatePicker)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.date)
            )
        }
    )
}

@Composable
private fun CustomDropdownMenu(
    @StringRes
    items: List<Int>,
    isExpanded: Boolean,
    onDismiss: () -> Unit,
    onChooseItem: (String) -> Unit
) {
    val context = LocalContext.current

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss
    ) {
        items.forEach { stringResId ->
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = stringResId))
                },
                onClick = {
                    onChooseItem(context.getString(stringResId))
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomDatePickerDialog(
    onEvent: (FeedbackScreenEvent) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }
    val millisToLocalDate = remember {
        derivedStateOf {
            datePickerState.selectedDateMillis?.let {
                DateUtil.convertMillisToLocalDate(
                    it
                )
            }
        }
    }
    val localDateToString = remember {
        derivedStateOf {
            millisToLocalDate.value?.let {
                DateUtil.convertLocalDateToString(it)
            }
        }
    }
    DatePickerDialog(
        onDismissRequest = {
            onEvent(
                FeedbackScreenEvent.ToggleDatePicker
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onEvent(
                        FeedbackScreenEvent.ToggleDatePicker
                    )
                    localDateToString.value?.let { pickedDate ->
                        onEvent(
                            FeedbackScreenEvent.Date(
                                value = pickedDate
                            )
                        )
                    }
                },
                enabled = confirmEnabled.value
            ) {
                Text(text = stringResource(R.string.ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onEvent(
                        FeedbackScreenEvent.ToggleDatePicker
                    )
                }
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
private fun ResetButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClicked,
        shape = RectangleShape
    ) {
        Text(text = stringResource(R.string.reset))
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PositionSpinnerPreview() {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    ITTHelperTheme {
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(text = "Toggle")
        }
        CustomDropdownMenu(
            items = PositionMenuItems.items,
            isExpanded = isExpanded,
            onDismiss = {
                isExpanded = false
            },
            onChooseItem = {}
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun FeedbackContentPreview() {
    val state = FeedbackScreenState(
        positionDropdownMenuState = PositionDropdownMenuState(
            items = PositionMenuItems.items
        ),
        typeDropdownMenuState = TypeDropdownMenuState(
            items = TypeMenuItems.items
        ),
        datePickerState = DatePickerState()
    )

    ITTHelperTheme {
        FeedbackContent(
            navController = rememberNavController(),
            state = state,
            onEvent = {}
        )
    }
}
