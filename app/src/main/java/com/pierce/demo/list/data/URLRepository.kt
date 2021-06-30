package com.pierce.demo.list.data

import android.util.Log
import com.pierce.demo.list.network.NetworkMonitor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * This class is a interface for ViewModel to get URL GET result.
 */
class URLRepository {
    interface RequestCallback {
        fun onSuccess(result: String?)
        fun onFail(result: String)
    }

    fun getUrlContent(callback: RequestCallback) {
        val url = if (NetworkMonitor.isWIFIConnected) PUBLIC_URL else PUBLIC_URL
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val api : Api = retrofit.create(Api::class.java)
        val request : Call<String> = api.fetchContent()
        request.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onFail("connect fail")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i(TAG, "success "+ response.body())
                callback.onSuccess(response.body())
            }
        })

    }

    companion object {
        const val WIFI_URL = "http://192.168.2.2/"
        const val PUBLIC_URL = "https://code-test.migoinc-dev.com/"
        var TAG = URLRepository::class.java.simpleName

    }
}