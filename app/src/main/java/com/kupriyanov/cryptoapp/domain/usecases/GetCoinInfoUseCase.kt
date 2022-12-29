package com.kupriyanov.cryptoapp.domain.usecases

import com.kupriyanov.cryptoapp.domain.CoinRepository

class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fromSymbol: String) = repository.getCoin(fromSymbol)
}