package com.example.weatherapp.core.failure

data class LocalFailure(
    val errMsg: String,
    val errName: String
): AppFailure(errMsg,errName){
    companion object {
        fun fromException(exception: Exception): LocalFailure {
            return LocalFailure(
                errName = exception::class.java.simpleName,
                errMsg = exception.message ?: "Unknown local error"
            )
        }
    }
}