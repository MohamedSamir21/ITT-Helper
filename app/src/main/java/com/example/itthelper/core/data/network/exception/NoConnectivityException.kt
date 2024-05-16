package com.example.itthelper.core.data.network.exception

import java.io.IOException

class NoConnectivityException: IOException() {
    override val message: String
        get() = "No Internet Connection"
}