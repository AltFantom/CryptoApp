package com.kupriyanov.cryptoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfo(
    @SerializedName("PRICE")
    @Expose
    val price: Double? = null
)