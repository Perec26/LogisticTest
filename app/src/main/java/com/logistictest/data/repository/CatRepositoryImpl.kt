package com.logistictest.data.repository

import com.logistictest.data.model.toDomain
import com.logistictest.data.model.toEntity
import com.logistictest.data.source.CatApi
import com.logistictest.data.source.CatDao
import com.logistictest.domain.model.Cat
import com.logistictest.domain.repository.CatRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class CatRepositoryImpl(
    private val api: CatApi,
    private val dao: CatDao
) : CatRepository {

    override fun loadCats(page: Int): Single<List<Cat>> {
        return api.getCats(SEARCH_LIMIT, page).map { it.toDomain() }
    }


    override fun getFavoriteCats(): Observable<List<Cat>> {
        return dao.getCatsAsObservable().map { it.toDomain() }
    }

    override fun saveCat(cat: Cat): Completable {
        return Completable.create {
            dao.insertCat(cat.toEntity())
            it.onComplete()
        }
    }

    override fun deleteCat(cat: Cat): Completable {
        return Completable.create {
            dao.deleteCat(cat.id)
            it.onComplete()
        }
    }

    companion object {
        private const val SEARCH_LIMIT = 20
    }
}