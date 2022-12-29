package com.kupriyanov.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val imageUrl: String?,
    val lowDay: Double?,
    val highDay: Double?,
    val lastMarket: String?
)

