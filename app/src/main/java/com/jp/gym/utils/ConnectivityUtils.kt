package com.jp.gym.utils

import android.content.Context
import android.net.ConnectivityManager

object ConnectivityUtils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager
            .activeNetworkInfo.isConnectedOrConnecting
    }

}
