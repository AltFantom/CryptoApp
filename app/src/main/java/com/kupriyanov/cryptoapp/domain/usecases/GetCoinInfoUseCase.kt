package com.kupriyanov.cryptoapp.domain.usecases

import com.kupriyanov.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
    ) {
    operator fun invoke(fromSymbol: String) = repository.getCoin(fromSymbol)
}