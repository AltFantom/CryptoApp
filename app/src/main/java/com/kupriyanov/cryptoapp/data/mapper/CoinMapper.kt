package com.kupriyanov.cryptoapp.data.mapper

import com.google.gson.Gson
import com.kupriyanov.cryptoapp.data.database.CoinInfoDbModel
import com.kupriyanov.cryptoapp.data.network.model.CoinInfoDto
import com.kupriyanov.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.kupriyanov.cryptoapp.data.network.model.CoinNamesListDto
import com.kupriyanov.cryptoapp.domain.entities.CoinInfo

class CoinMapper {

    fun mapDtoToDbModel(coinPriceDto: CoinInfoDto): CoinInfoDbModel {
        with(coinPriceDto) {
            return CoinInfoDbModel(
                fromSymbol,
                toSymbol,
                price,
                lastUpdate,
                imageUrl,
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
                lastUpdate,
                imageUrl,
                lowDay,
                highDay,
                lastMarket
            )
        }
    }


}