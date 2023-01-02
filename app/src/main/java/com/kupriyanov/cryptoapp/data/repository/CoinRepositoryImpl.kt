package com.kupriyanov.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.kupriyanov.cryptoapp.data.database.CoinInfoDao
import com.kupriyanov.cryptoapp.data.mapper.CoinMapper
import com.kupriyanov.cryptoapp.domain.CoinRepository
import com.kupriyanov.cryptoapp.domain.entities.CoinInfo
import com.kupriyanov.cryptoapp.workers.RefreshDataWorker
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor (
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val mapper: CoinMapper
) : CoinRepository {

//    private val coinPriceInfoDao = AppDatabase
//        .getInstance(application)
//        .coinPriceInfoDao()

    //private val mapper = CoinMapper()

    override fun getCoinList(): LiveData<List<CoinInfo>> = Transformations.map(
        coinInfoDao.getPriceList()
    ) {
        it.map { coinInfoDbModel ->
            mapper.mapDbModelToEntity(coinInfoDbModel)
        }
    }

    override fun getCoin(fromSymbol: String): LiveData<CoinInfo> = Transformations.map(
        coinInfoDao.getPriceInfoAboutCoin(fromSymbol)
    ) {
        mapper.mapDbModelToEntity(it)
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.createOneTimeWorkRequest()
        )
    }
}