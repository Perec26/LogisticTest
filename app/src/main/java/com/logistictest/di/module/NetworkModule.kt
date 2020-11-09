package com.logistictest.di.module

import android.os.Build
import com.logistictest.BuildConfig
import com.logistictest.base.HttpLogger
import com.logistictest.base.Tls12SocketFactory.Companion.enableTls12
import com.logistictest.data.source.CatApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun moshi() = Moshi.Builder().add((KotlinJsonAdapterFactory())).build()

    @Provides
    fun moshiConverterFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi)

    @Provides
    fun httpLoggingInterceptor() = HttpLoggingInterceptor(HttpLogger())

    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    enableTls12()
                }
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.REST_API_ADDRESS)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun catApi(retrofit: Retrofit) = retrofit.create(CatApi::class.java)


}