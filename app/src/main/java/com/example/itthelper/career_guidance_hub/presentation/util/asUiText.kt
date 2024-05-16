package com.example.itthelper.career_guidance_hub.presentation.util

import com.example.itthelper.R
import com.example.itthelper.core.domain.error.DataError

fun DataError.Network.asUiText(): UiText {
    return when (this) {
        DataError.Network.UNAUTHORIZED -> UiText.StringResource(
            R.string.your_access_has_expired
        )

        DataError.Network.NO_INTERNET_CONNECTION -> UiText.StringResource(
            R.string.no_internet_connection
        )

        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.the_request_timed_out
        )

        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.server_error
        )

        DataError.Network.UNKNOWN -> UiText.StringResource(
            R.string.unknown_error
        )
    }
}
