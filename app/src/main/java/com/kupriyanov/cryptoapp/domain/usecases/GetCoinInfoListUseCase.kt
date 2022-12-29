package com.kupriyanov.cryptoapp.domain.usecases

import com.kupriyanov.cryptoapp.domain.CoinRepository

class GetCoinInfoListUseCase(private val repository: CoinRepository) {
    operator fun invoke() = repository.getCoinList()
}