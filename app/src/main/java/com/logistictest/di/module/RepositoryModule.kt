package com.logistictest.di.module

import android.app.DownloadManager
import android.content.Context
import com.logistictest.data.repository.CatRepositoryImpl
import com.logistictest.data.repository.DownloadRepositoryImpl
import com.logistictest.data.source.CatApi
import com.logistictest.data.source.CatDao
import com.logistictest.domain.repository.CatRepository
import com.logistictest.domain.repository.DownloadRepository
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        NetworkModule::class,
        DbModule::class
    ]
)
class RepositoryModule {

    @Provides
    fun downloadManager(context: Context): DownloadManager {
        return context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    @Provides
    fun catRepository(api: CatApi, dao: CatDao): CatRepository = CatRepositoryImpl(api, dao)

    @Provides
    fun downloadRepository(manager: DownloadManager,context: Context): DownloadRepository {
        return DownloadRepositoryImpl(manager,context)
    }
}
