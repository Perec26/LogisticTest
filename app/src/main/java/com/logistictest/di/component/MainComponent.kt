package com.logistictest.di.component

import android.content.Context
import com.logistictest.di.module.ContextModule
import com.logistictest.di.module.NavigationModule
import com.logistictest.navigation.MainNavigator
import com.logistictest.presentation.main.MainActivity
import com.logistictest.presentation.main.MainPresenter
import dagger.Component

@Component(modules = [NavigationModule::class, ContextModule::class])
interface MainComponent {
    fun getContext(): Context
    fun getMainPresenter(): MainPresenter
    fun getNavigator(): MainNavigator
    fun inject(act: MainActivity)
}