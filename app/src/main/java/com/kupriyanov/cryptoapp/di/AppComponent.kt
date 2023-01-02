package com.kupriyanov.cryptoapp.di

import android.app.Application
import com.kupriyanov.cryptoapp.presentation.activities.CoinDetailFragment
import com.kupriyanov.cryptoapp.presentation.activities.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(coinDetailFragment: CoinDetailFragment)

    fun inject(activity: CoinPriceListActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}