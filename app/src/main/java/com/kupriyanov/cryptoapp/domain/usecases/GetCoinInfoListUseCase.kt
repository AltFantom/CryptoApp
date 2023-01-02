package com.kupriyanov.cryptoapp.domain.usecases

import com.kupriyanov.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor(
    private val repository: CoinRepository
    ) {
    operator fun invoke() = repository.getCoinList()
}