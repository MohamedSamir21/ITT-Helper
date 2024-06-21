package com.example.itthelper.career_guidance_hub.presentation.feedback

import androidx.annotation.StringRes
import com.example.itthelper.career_guidance_hub.presentation.util.UiText

data class FeedbackScreenState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val message: String = "",
    val positionDropdownMenuState: PositionDropdownMenuState = PositionDropdownMenuState(
        items = PositionMenuItems.items
    ),
    val typeDropdownMenuState: TypeDropdownMenuState = TypeDropdownMenuState(
        items = TypeMenuItems.items
    ),
    val datePickerState: DatePickerState = DatePickerState(),
    val dialogTitle: UiText? = null,
    var dialogBody: UiText = UiText.DynamicString(""),
    var showResultDialog: Boolean = false,
    var isSending: Boolean = false
)

data class PositionDropdownMenuState(
    @StringRes
    val items: List<Int>,
    val selectedPosition: String = "",
    val showPositionDropdownMenu: Boolean = false
)

data class TypeDropdownMenuState(
    @StringRes
    val items: List<Int>,
    val selectedType: String = "",
    val showTypeDropdownMenu: Boolean = false
)

data class DatePickerState(
    val selectedDate: String = "",
    val showDatePicker: Boolean = false
)
