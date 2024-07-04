package com.example.weatherapp.core.failure

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException

data class NetworkFailure(
    val errName: String,
    val errMsg: String,
    val uriPath: String,
    val statusCode: Int
) : AppFailure(errMsg, errName) {
    companion object {
        private val gson = Gson()

        fun fromException(exception: HttpException): NetworkFailure {
            val response = exception.response()
            val errorBody = response?.errorBody()?.string() ?: "{}"
            val errorMsgMap = parseErrorMessage(errorBody)

            var errorMsg = ""
            if (errorMsgMap.containsKey("message")) {
                errorMsg = errorMsgMap["message"].toString()
            } else if (errorMsgMap.containsKey("error")) {
                errorMsg = errorMsgMap["error"].toString()
            } else if (errorMsgMap.containsKey("errors")) {
                errorMsg = errorMsgMap["errors"].toString()
            }

            return NetworkFailure(
                errName = response?.message() ?: "Network Error",
                errMsg = errorMsg,
                uriPath = response?.raw()?.request?.url.toString(),
                statusCode = response?.code() ?: 0
            )
        }

        private fun parseErrorMessage(errorBody: String): Map<String, Any?> {
            return try {
                gson.fromJson(errorBody, object : TypeToken<Map<String, Any?>>() {}.type)
            } catch (e: Exception) {
                emptyMap()
            }
        }
    }
}