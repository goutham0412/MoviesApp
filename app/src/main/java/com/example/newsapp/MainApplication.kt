package com.example.newsapp

import android.app.Application
import com.example.newsapp.data.di.dataDi
import com.example.newsapp.domain.di.domainModule
import com.example.newsapp.presentation.di.dimodule
import org.koin.android.ext.android.getKoin
import org.koin.core.context.startKoin

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dimodule, domainModule, dataDi)
        }
    }

}