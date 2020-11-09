package com.logistictest.presentation.favorites

import com.logistictest.presentation.model.CatUi
import moxy.MvpView

interface FavoritesView : MvpView {
    fun showLoading()
    fun showCats(cats: List<CatUi>)
    fun hideLoading()
}