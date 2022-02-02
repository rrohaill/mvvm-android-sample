package com.rrohaill.refactoringsample.api

import com.rrohaill.refactoringsample.data.Places
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("meappid")
    suspend fun getPlaces(
        @Query("meAppId") id: Int,
        @Query("records") records: Int
    ): Response<Places>

}