package com.kupriyanov.cryptoapp.presentation

import android.app.Application
import com.kupriyanov.cryptoapp.di.DaggerAppComponent

class CoinApp: Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}