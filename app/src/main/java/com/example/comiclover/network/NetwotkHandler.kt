package com.example.comiclover.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

//@Singleton
class NetworkHandler
//@Inject constructor(@ApplicationContext private val context: Context) {
constructor(private val context: Context) {
    val isConnected: Boolean
        get() {
            val connectivityManager = context.connectivityManager
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        }
}