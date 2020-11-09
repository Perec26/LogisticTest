package com.logistictest.di.module

import android.content.Context
import com.logistictest.base.Database
import com.logistictest.di.provider.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class DbModule {
    @Provides
    fun database(context: Context) = DatabaseProvider(context).get()

    @Provides
    fun catDao(database: Database) = database.catDao()
}