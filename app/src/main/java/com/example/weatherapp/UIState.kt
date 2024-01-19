package com.example.weatherapp

sealed class UIState<out T> {
    data object Idle : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Failure(val exception: Exception) : UIState<Nothing>()
}
