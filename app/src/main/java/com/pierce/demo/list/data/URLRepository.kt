package com.pierce.demo.list.data

import android.util.Log
import com.pierce.demo.list.network.NetworkMonitor
import okhttp3.*
import java.io.IOException

/**
 * This class is a interface for ViewModel to get URL GET result.
 */
class URLRepository {
    interface RequestCallback {
        fun onSuccess(result: String)
        fun onFail(result: String)
    }

    fun getUrlContent(callback: RequestCallback) {
        val url = if (NetworkMonitor.isWIFIConnected) WIFI_URL else PUBLIC_URL
        val request: Request = Request.Builder()
                .url(url)
                .build()
        val call: Call = client.newCall(request)
        call.enqueue(object : Callback {
            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                val result: String = response.body().string()
                Log.d("TAG", result)
                callback.onSuccess(result)
            }

            override fun onFailure(call: Call?, e: IOException?) {
                callback.onFail("connect fail")
            }
        })
    }

    companion object {
        const val WIFI_URL = "http://192.168.2.2/status"
        const val PUBLIC_URL = "https://code-test.migoinc-dev.com/status"
        var TAG = URLRepository::class.java.simpleName
        var client: OkHttpClient = OkHttpClient().newBuilder().build()
    }
}