package com.logistictest.presentation.catlist

import com.logistictest.domain.interactor.CatInteractor
import com.logistictest.domain.model.Cat
import com.logistictest.navigation.MainNavigator
import com.logistictest.presentation.model.CatUi
import com.logistictest.presentation.model.toDomain
import com.logistictest.presentation.model.toUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CatListPresenter @Inject constructor(
    private val catInteractor: CatInteractor,
    private val navigator: MainNavigator
) : MvpPresenter<CatListView>() {

    private val catsSubject = BehaviorSubject.create<List<Cat>>()
    private var page = 0
    private var disposables = CompositeDisposable()
    private var cashedCats = listOf<CatUi>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        subscribeCats()
        getCats()
    }

    private fun subscribeCats() {
        Observables.combineLatest(catsSubject, catInteractor.getFavoriteCat()) { netCats, dbCats ->
            netCats.map { netCat ->
                netCat.toUi(dbCats.any { it.id == netCat.id })
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .doOnNext { viewState.hideLoading() }
            .subscribeBy(
                onNext = {
                    cashedCats = it
                    viewState.showCats(it)
                },
                onError = {
                    it.printStackTrace()
                }
            )
            .addTo(disposables)
    }

    private fun getCats() {
        catInteractor.getCat(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val newCats = (catsSubject.value ?: listOf()) + it
                    catsSubject.onNext(newCats)
                },
                onError = {
                    viewState.showError()
                }
            )
            .addTo(disposables)
    }

    fun onCatClick(catUi: CatUi) {
        val cat = cashedCats.find { it.id == catUi.id } ?: return
        if (cat.isFavorite) {
            catInteractor.deleteCat(cat.toDomain())
        } else {
            catInteractor.saveCat(cat.toDomain())
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {},
                onError = { viewState.showError() }
            )
            .addTo(disposables)
    }

    fun loadNextPage() {
        page++
        getCats()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    fun onFavoritesClick() {
        navigator.toFavorites()
    }

    fun onRetryClick() {
        getCats()
    }

    fun onDownloadClick(catUi: CatUi) {
        catInteractor.downloadImage(catUi.toDomain())
    }
}