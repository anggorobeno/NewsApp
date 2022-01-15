package com.example.newsapp.core.utils

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<T>(val message: String, val data: T? = null) : UiState<T>()
    object Loading : UiState<Nothing>()
}
