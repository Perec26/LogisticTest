package com.logistictest.data.source

import com.logistictest.data.model.CatDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("search")
    fun getCats(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Single<List<CatDto>>
}