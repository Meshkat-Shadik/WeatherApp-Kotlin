package com.example.weatherapp.core.failure

sealed class AppFailure(
    val message: String,
    val name: String,
    uriPath: String? = null,
    code: Int? = null
)