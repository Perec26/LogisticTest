package com.logistictest.base

import androidx.multidex.MultiDexApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        RxJavaPlugins.setErrorHandler { Timber.e(it) }
    }
}