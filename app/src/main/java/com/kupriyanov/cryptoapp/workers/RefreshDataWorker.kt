package com.kupriyanov.cryptoapp.workers

import android.content.Context
import androidx.work.*
import com.kupriyanov.cryptoapp.data.database.CoinInfoDao
import com.kupriyanov.cryptoapp.data.mapper.CoinMapper
import com.kupriyanov.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {

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

    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinMapper
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                coinInfoDao,
                apiService,
                mapper
            )
        }
    }
}