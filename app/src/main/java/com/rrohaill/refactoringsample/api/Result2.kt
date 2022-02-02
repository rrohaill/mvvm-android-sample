package com.rrohaill.refactoringsample.api

import com.rrohaill.refactoringsample.data.ApiError

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */

sealed class Result2<out T> {
    data class Success<T>(val data: T) : Result2<T>()
    data class Error<T>(val message: String, val error: ApiError) : Result2<T>()
}