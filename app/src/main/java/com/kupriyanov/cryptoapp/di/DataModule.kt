package com.kupriyanov.cryptoapp.di

import android.app.Application
import com.kupriyanov.cryptoapp.data.database.AppDatabase
import com.kupriyanov.cryptoapp.data.database.CoinInfoDao
import com.kupriyanov.cryptoapp.data.network.ApiFactory
import com.kupriyanov.cryptoapp.data.network.ApiService
import com.kupriyanov.cryptoapp.data.repository.CoinRepositoryImpl
import com.kupriyanov.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @AppScope
    @Binds
    fun provideCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @AppScope
        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @AppScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}