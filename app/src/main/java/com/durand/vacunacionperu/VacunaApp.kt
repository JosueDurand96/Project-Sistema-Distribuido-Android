package com.durand.vacunacionperu

import android.app.Application
import com.durand.vacunacionperu.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VacunaApp: Application() {

    companion object {
        private lateinit var instance: VacunaApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@VacunaApp)
            modules(applicationModule)
        }
    }

}