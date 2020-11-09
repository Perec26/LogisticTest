package com.logistictest.domain.repository

import com.logistictest.domain.model.Cat
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CatRepository {
    fun getFavoriteCats(): Observable<List<Cat>>
    fun saveCat(cat: Cat): Completable
    fun deleteCat(cat: Cat): Completable
    fun loadCats(page: Int): Single<List<Cat>>
}