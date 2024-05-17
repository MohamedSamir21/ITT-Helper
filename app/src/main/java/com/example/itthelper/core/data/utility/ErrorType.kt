package com.example.itthelper.core.data.utility

import com.example.itthelper.core.domain.error.DataError


fun Int.getHttpErrorType(): DataError.Network {
    return when (this) {
        401 -> DataError.Network.UNAUTHORIZED
        408 -> DataError.Network.REQUEST_TIMEOUT
        500 -> DataError.Network.SERVER_ERROR
        else -> DataError.Network.UNKNOWN
    }
}