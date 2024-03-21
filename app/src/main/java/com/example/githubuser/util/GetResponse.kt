package com.example.githubuser.util

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <reified T> getResponse(
    crossinline block: suspend () -> Response<T>
) = flow {
    emit(Resource.Loading())

    delay(600)

    try {
        val res = block()

        if (!res.isSuccessful) {
            emit(Resource.Error(res.message()))
        }

        emit(Resource.Success(res.body()))
    } catch (e: Exception) {
        Log.e("API CALL ERROR", e.message.toString())
        emit(Resource.Error(e.message))
    }
}