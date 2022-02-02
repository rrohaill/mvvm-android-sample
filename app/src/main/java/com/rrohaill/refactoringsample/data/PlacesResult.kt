package com.rrohaill.refactoringsample.data

sealed class PlacesResult {
    data class Success(val data: Places) : PlacesResult()
    data class Error(val error: ApiError) : PlacesResult()
}
