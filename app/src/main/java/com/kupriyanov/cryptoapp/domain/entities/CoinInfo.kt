package com.kupriyanov.cryptoapp.domain.entities

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: String,
    val imageUrl: String,
    val lowDay: Double?,
    val highDay: Double?,
    val lastMarket: String?
)
