package com.kupriyanov.cryptoapp.di

import com.kupriyanov.cryptoapp.workers.ChildWorkerFactory
import com.kupriyanov.cryptoapp.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @WorkerKey(RefreshDataWorker::class)
    @IntoMap
    fun bindRefreshDataWorkerFactory(
        refreshDataWorker: RefreshDataWorker.Factory
    ): ChildWorkerFactory
}