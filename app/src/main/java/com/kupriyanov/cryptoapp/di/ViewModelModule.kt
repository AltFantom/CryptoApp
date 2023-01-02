package com.kupriyanov.cryptoapp.di

import androidx.lifecycle.ViewModel
import com.kupriyanov.cryptoapp.presentation.viewModels.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(viewModel: CoinViewModel): ViewModel
}