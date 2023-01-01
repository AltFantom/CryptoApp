package com.kupriyanov.cryptoapp.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kupriyanov.cryptoapp.data.repository.CoinRepositoryImpl
import com.kupriyanov.cryptoapp.domain.usecases.GetCoinInfoListUseCase
import com.kupriyanov.cryptoapp.domain.usecases.GetCoinInfoUseCase
import com.kupriyanov.cryptoapp.domain.usecases.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}