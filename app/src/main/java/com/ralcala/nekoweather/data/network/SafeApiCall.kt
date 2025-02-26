package com.ralcala.nekoweather.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Log.e("THROWABLE", "$throwable")
                if (throwable is SocketTimeoutException) {
                    Log.e("THROWABLE", "$throwable")
                }
                when (throwable) {
                    is HttpException -> {
                        Resource.Failed(false, throwable.code(), throwable.response()?.errorBody())
                    }

                    is SocketTimeoutException -> {
                        Resource.Failed(true, 1, null)
                    }

                    else -> {
                        Resource.Failed(true, null, null)
                    }
                }
            }
        }
    }
}