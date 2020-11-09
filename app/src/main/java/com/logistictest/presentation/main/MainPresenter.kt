package com.logistictest.presentation.main

import com.logistictest.navigation.MainNavigator
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val navigator: MainNavigator
) : MvpPresenter<MainView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        navigator.toCatList()
    }


}