package com.logistictest.presentation.favorites

import com.logistictest.domain.interactor.CatInteractor
import com.logistictest.navigation.MainNavigator
import com.logistictest.presentation.model.CatUi
import com.logistictest.presentation.model.toDomain
import com.logistictest.presentation.model.toUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(
    private val catInteractor: CatInteractor,
    private val navigator: MainNavigator
) : MvpPresenter<FavoritesView>() {

    private var disposables = CompositeDisposable()

    fun onCatClick(cat: CatUi) {
        catInteractor.deleteCat(cat.toDomain())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposables)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCats()
    }

    private fun getCats() {
        catInteractor.getFavoriteCat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .doOnNext { viewState.hideLoading() }
            .subscribeBy(
                onNext = { viewState.showCats(it.toUi(true)) },
                onError = { it.printStackTrace() }
            )
            .addTo(disposables)
    }

    fun onBackPressed() {
        navigator.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    fun onDownloadClick(cat: CatUi) {
        catInteractor.downloadImage(cat.toDomain())
    }
}