package com.kupriyanov.cryptoapp.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDto(
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,
    @PrimaryKey()
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,
    @SerializedName("PRICE")
    @Expose
    val price: Double?,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long?,
    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double?,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double?,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?,
)

