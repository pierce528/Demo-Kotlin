package com.pierce.demo.list

import android.app.Application
import com.pierce.demo.list.network.NetworkMonitor

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instace = this
        val monitor = NetworkMonitor(this)

    }

    companion object {
        var instace: Application? = null
    }
}