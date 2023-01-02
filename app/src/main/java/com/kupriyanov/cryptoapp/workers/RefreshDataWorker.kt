package com.kupriyanov.cryptoapp.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.kupriyanov.cryptoapp.data.database.AppDatabase
import com.kupriyanov.cryptoapp.data.database.CoinInfoDao
import com.kupriyanov.cryptoapp.data.mapper.CoinMapper
import com.kupriyanov.cryptoapp.data.network.ApiFactory
import com.kupriyanov.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            val topCoins = apiService.getTopCoinsInfo(limit = 50)
            val fromSymbol = mapper.mapNamesListToString(topCoins)
            val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbol)
            val coinDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
            val dbModelList = coinDtoList.map { mapper.mapDtoToDbModel(it) }
            coinInfoDao.insertPriceList(dbModelList)
            delay(10000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun createOneTimeWorkRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}