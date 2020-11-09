package com.logistictest.presentation.catlist

import com.logistictest.presentation.model.CatUi
import moxy.MvpView

interface CatListView : MvpView {
    fun showLoading()
    fun showCats(cats: List<CatUi>)
    fun hideLoading()
    fun showError()
}