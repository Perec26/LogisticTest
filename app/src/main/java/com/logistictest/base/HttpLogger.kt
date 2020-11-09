package com.logistictest.base

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

internal class HttpLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) = Timber.d(message)
}