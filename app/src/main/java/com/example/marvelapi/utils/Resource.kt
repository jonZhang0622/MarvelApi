package com.example.marvelapi.utils

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val errorMsg: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
