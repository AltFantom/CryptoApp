package com.kupriyanov.cryptoapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.kupriyanov.cryptoapp.domain.usecases.GetCoinInfoListUseCase
import com.kupriyanov.cryptoapp.domain.usecases.GetCoinInfoUseCase
import com.kupriyanov.cryptoapp.domain.usecases.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}