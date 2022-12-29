package com.kupriyanov.cryptoapp.domain

import androidx.lifecycle.LiveData
import com.kupriyanov.cryptoapp.domain.entities.CoinInfo

interface CoinRepository {

    fun getCoinList(): LiveData<List<CoinInfo>>

    fun getCoin(fromSymbol: String): LiveData<CoinInfo>

    suspend fun loadData()
}