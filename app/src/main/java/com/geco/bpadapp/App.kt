package com.geco.bpadapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.geco.bpadapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    val db_name = "bpad_app_db"
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }


    override fun onCreate() {
        super.onCreate()
        context = this
        // Mulai Koin dan daftar modul-modul yang dibutuhkan
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}