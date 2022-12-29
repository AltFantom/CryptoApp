package com.kupriyanov.cryptoapp.domain.usecases

import com.kupriyanov.cryptoapp.domain.CoinRepository

class LoadDataUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke() = repository.loadData()
}