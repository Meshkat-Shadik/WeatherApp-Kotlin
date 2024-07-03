package com.example.weatherapp.core.failure

import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

object FailureMapper {
    fun getFailure(exception: Exception): AppFailure {
        return when (exception) {
            is HttpException -> NetworkFailure.fromException(exception)
            else -> LocalFailure.fromException(exception)
        }
    }

    fun getFailureFromResponse(response: Response<*>): AppFailure {
        return NetworkFailure(
            errName = response.message(),
            errMsg = response.errorBody()?.string() ?: "Unknown network error",
            uriPath = response.raw().request.url.toString(),
            statusCode = response.code()
        )
    }
}
