package com.rrohaill.refactoringsample.api

import android.content.Context
import retrofit2.create

object ApiFactory {
    private lateinit var myApi: MyApi

    private const val BASE_URL = "https://secure.closepayment.com/close-admin/1.0/place/"

    fun getApi(context: Context): MyApi {
        if (!ApiFactory::myApi.isInitialized) {
            myApi = RetrofitFactory.retrofit(
                baseUrl = BASE_URL,
                context = context
            ).create()
        }
        return myApi
    }
}