package com.example.githubuser.util

sealed class
Resource<T>(
    open val data: T? = null,
    open val message: String? = null
) {

    data class Success<T>(override val data: T) : Resource<T>(data)

    data class Error<T>(override val message: String?, override val data: T? = null) : Resource<T>(data, message)

    class Loading<T> : Resource<T>()

    class NotLoadedYet<T>: Resource<T>()
}