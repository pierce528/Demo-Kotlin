package com.pierce.demo.list.data

import retrofit2.Call
import retrofit2.http.GET

interface Api {

        @GET("status")
        fun fetchContent() : Call<String>
}
