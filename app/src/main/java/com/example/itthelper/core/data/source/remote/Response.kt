package com.example.itthelper.core.data.source.remote

import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "response")
    val message: String
)
