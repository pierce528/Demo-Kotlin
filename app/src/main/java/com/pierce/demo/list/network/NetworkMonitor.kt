package com.pierce.demo.list.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

class NetworkMonitor(var mContext: Context) {
    var connectivityManager: ConnectivityManager

    companion object {
        var TAG = NetworkMonitor::class.java.simpleName
        var isCellularConnected = false
        var isWIFIConnected = false

    }

    init {
        connectivityManager =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        currState()
        registerNetworkCallback()
    }


    fun currState(){
        val currentNetwork = connectivityManager.activeNetwork
        val caps = connectivityManager.getNetworkCapabilities(currentNetwork)
        isCellularConnected =
        caps!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        isWIFIConnected = caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

    fun registerNetworkCallback() {
        try {
            val builder = NetworkRequest.Builder()
            builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            val request = builder.build()
            connectivityManager.registerNetworkCallback(request, object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isCellularConnected = true // Global Static Variable
                    Log.i(TAG, "cellular is connected")
                }

                override fun onLost(network: Network) {
                    isCellularConnected = false // Global Static Variable
                    Log.i(TAG, "cellular is lost")
                }
            })
        } catch (e: Exception) {
            isCellularConnected = false
        }
        try {
            val builder = NetworkRequest.Builder()
            builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            val request = builder.build()
            connectivityManager.registerNetworkCallback(request, object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isWIFIConnected = true // Global Static Variable
                    Log.i(TAG, "wifi is connected")
                }

                override fun onLost(network: Network) {
                    isWIFIConnected = false // Global Static Variable
                    Log.i(TAG, "wifi is lost")
                }
            })
        } catch (e: Exception) {
            isWIFIConnected = false
        }
    }


}