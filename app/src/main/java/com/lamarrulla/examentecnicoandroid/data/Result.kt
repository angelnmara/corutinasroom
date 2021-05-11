package com.lamarrulla.examentecnicoandroid.data

sealed class Result<out T : Any> {

    class Loading<out T : Any>: Result<T>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading->"Loading[]"
            else -> "Else[]"
        }
    }
}