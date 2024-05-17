package com.example.itthelper.core.domain.error

sealed interface DataError : Error {
    enum class Network : DataError {
        UNAUTHORIZED,
        NO_INTERNET_CONNECTION,
        REQUEST_TIMEOUT,
        SERVER_ERROR,
        UNKNOWN
    }
}