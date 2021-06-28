package com.pierce.demo.list

import android.app.Application
import com.pierce.demo.list.module.appModule
import com.pierce.demo.list.network.NetworkMonitor
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        val monitor = NetworkMonitor(this)

        startKoin { modules(listOf(appModule)) }

    }

    companion object {
        lateinit var INSTANCE: Application

        fun getInstance() : Application {
            return INSTANCE
        }
    }
}