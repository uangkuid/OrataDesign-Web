package com.oratakashi.design.docs

import android.app.Application
import com.oratakashi.design.docs.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(*AppModule.provideModule())
        }
    }
}