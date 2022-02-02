package com.rrohaill.refactoringsample.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiError(
    @SerializedName("type") val type: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("description") val description: String = ""
)