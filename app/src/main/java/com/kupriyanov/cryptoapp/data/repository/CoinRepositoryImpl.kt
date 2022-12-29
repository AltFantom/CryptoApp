package com.kupriyanov.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kupriyanov.cryptoapp.data.database.AppDatabase
import com.kupriyanov.cryptoapp.data.mapper.CoinMapper
import com.kupriyanov.cryptoapp.data.network.ApiFactory
import com.kupriyanov.cryptoapp.domain.CoinRepository
import com.kupriyanov.cryptoapp.domain.entities.CoinInfo
import kotlinx.coroutines.delay

class CoinRepositoryImpl(private val application: Application) : CoinRepository {

    private val coinPriceInfoDao = AppDatabase
        .getInstance(application)
        .coinPriceInfoDao()

    private val apiService = ApiFactory.apiService

    private val mapper = CoinMapper()

    override fun getCoinList(): LiveData<List<CoinInfo>> = Transformations.map(
        coinPriceInfoDao.getPriceList()
    ) {
        it.map { coinInfoDbModel ->
            mapper.mapDbModelToEntity(coinInfoDbModel)
        }
    }

    override fun getCoin(fromSymbol: String): LiveData<CoinInfo> = Transformations.map(
        coinPriceInfoDao.getPriceInfoAboutCoin(fromSymbol)
    ) {
        mapper.mapDbModelToEntity(it)
    }

    override suspend fun loadData() {
        while (true) {
            val topCoins = apiService.getTopCoinsInfo(limit = 50)
            val fromSymbol = mapper.mapNamesListToString(topCoins)
            val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbol)
            val coinDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
            val dbModelList = coinDtoList.map { mapper.mapDtoToDbModel(it) }
            coinPriceInfoDao.insertPriceList(dbModelList)
            delay(10000)
        }
    }

}