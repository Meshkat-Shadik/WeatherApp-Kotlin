package com.example.weatherapp.core.wrapper

import com.example.weatherapp.core.failure.AppFailure

sealed class UIState<out T> {
    data object Idle : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Failure(val failure: AppFailure) : UIState<Nothing>()
}