package com.kupriyanov.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.kupriyanov.cryptoapp.di.DaggerAppComponent
import com.kupriyanov.cryptoapp.workers.CoinWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var coinWorkerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(coinWorkerFactory)
            .build()
    }
}