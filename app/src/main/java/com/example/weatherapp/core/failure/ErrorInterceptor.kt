package com.example.weatherapp.core.failure

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import java.nio.Buffer
import java.util.Locale


class LoggingInterceptor : Interceptor {

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val t1 = System.nanoTime()
            var requestLog = String.format(
               "", request.url,
                /*chain.connection(),*/
                request.headers,
                request.method
            )
            //YTimber.d(String.format("Sending request %s on %s%n%s",
            //        request.url(), chain.connection(), request.headers()));
            if (request.method.compareTo("post", ignoreCase = true) == 0) {
                requestLog = requestLog + "\n" + bodyToString(request)
            } else if (request.method.compareTo("get", ignoreCase = true) != 0) {
                requestLog = requestLog + "\n" + bodyToString(request)
            }

                Log.d(TAG, "\nRequest Log:\n$requestLog")


            val response = chain.proceed(request)
            val t2 = System.nanoTime()

            val responseLog = String.format(
                Locale.US,
                "Received response for ",
                response.request.url, response.code, (t2 - t1) / 1e6, response.headers
            )

            val bodyString = response.body!!.string()

            Log.d(
                    TAG,
                    "\nResponse Log:\n${request.method} Method\n$responseLog\n$bodyString"
                )

            return response.newBuilder()
                .body(ResponseBody.create(response.body?.contentType(), bodyString))
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            var msg = ""
            when (e) {
                is SocketTimeoutException -> {
                    msg = "Timeout - Please check your internet connection"
                }
                is UnknownHostException -> {
                    msg = "Unable to make a connection. Please check your internet"
                }
                is ConnectionShutdownException -> {
                    msg = "Connection shutdown. Please check your internet"
                }
                is IOException -> {
                    msg = "Server is unreachable, please try again later."
                }
                is IllegalStateException -> {
                    msg = "${e.message}"
                }
                else -> {
                    msg = "${e.message}"
                }
            }
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body(ResponseBody.create(null, "{${e}}")).build()
        }
    }

    companion object {
        var TAG = LoggingInterceptor::class.java.simpleName


        /***
         * Covert Request data into string which we will need for logging.
         * @param request Latest request model from OkHTTP3
         * @return converted string body.
         */
        private fun bodyToString(request: Request): String {
            return try {
                val copy = request.newBuilder().build()
                val buffer = okio.Buffer()
                copy.body!!.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: IOException) {
                "${e.message}"
            }
        }
    }
}