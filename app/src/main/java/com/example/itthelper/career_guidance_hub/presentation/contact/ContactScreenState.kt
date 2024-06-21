package com.example.itthelper.career_guidance_hub.presentation.contact

import com.example.itthelper.career_guidance_hub.presentation.util.UiText

data class ContactScreenState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val subject: String = "",
    val message: String = "",
    val dialogTitle: UiText? = null,
    var dialogBody: UiText = UiText.DynamicString(""),
    var showResultDialog: Boolean = false,
    var isSending: Boolean = false
)
