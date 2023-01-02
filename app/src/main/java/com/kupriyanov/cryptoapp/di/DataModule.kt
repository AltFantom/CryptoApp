package com.kupriyanov.cryptoapp.di

import android.app.Application
import com.kupriyanov.cryptoapp.data.database.AppDatabase
import com.kupriyanov.cryptoapp.data.database.CoinInfoDao
import com.kupriyanov.cryptoapp.data.repository.CoinRepositoryImpl
import com.kupriyanov.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun provideCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinPriceInfo(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}