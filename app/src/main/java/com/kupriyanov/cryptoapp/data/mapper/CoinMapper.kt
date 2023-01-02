package com.kupriyanov.cryptoapp.data.mapper

import com.google.gson.Gson
import com.kupriyanov.cryptoapp.data.database.CoinInfoDbModel
import com.kupriyanov.cryptoapp.data.network.model.CoinInfoDto
import com.kupriyanov.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.kupriyanov.cryptoapp.data.network.model.CoinNamesListDto
import com.kupriyanov.cryptoapp.domain.entities.CoinInfo
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapDtoToDbModel(coinPriceDto: CoinInfoDto): CoinInfoDbModel {
        with(coinPriceDto) {
            return CoinInfoDbModel(
                fromSymbol,
                toSymbol,
                price,
                lastUpdate,
                BASE_IMAGE_URL + imageUrl,
                lowDay,
                highDay,
                lastMarket
            )
        }
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesList: CoinNamesListDto): String {
        return namesList.names?.map {
            it.coinInfo?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(coinInfoDbModel: CoinInfoDbModel): CoinInfo {
        with(coinInfoDbModel) {
            return CoinInfo(
                fromSymbol,
                toSymbol,
                price,
                convertTimestampToTime(lastUpdate),
                imageUrl,
                lowDay,
                highDay,
                lastMarket
            )
        }
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}