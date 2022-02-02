package com.rrohaill.refactoringsample.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasInternetConnection()) {
            throw IOException("No internet connection.")
        }
        return chain.proceed(chain.request())
    }

    private fun hasInternetConnection(): Boolean = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> check()
        else -> legacyCheck()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun check(): Boolean {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.let {
            it.getNetworkCapabilities(cm.activeNetwork)?.run {
                if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                ) {
                    return true
                }
            }
        }
        return false
    }

    @Suppress("DEPRECATION")
    private fun legacyCheck(): Boolean {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.let {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI ||
                    type == ConnectivityManager.TYPE_MOBILE
                ) {
                    return true
                }
            }
        }
        return false
    }

}