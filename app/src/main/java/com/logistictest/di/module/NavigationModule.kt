package com.logistictest.di.module

import com.logistictest.di.provider.CiceroneProvider
import com.logistictest.navigation.MainNavigator
import com.logistictest.navigation.MainNavigatorImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class NavigationModule {
    @Provides
    fun cicerone() = CiceroneProvider().get()

    @Provides
    fun router(cicerone: Cicerone<Router>) = cicerone.router

    @Provides
    fun mainNavigator(router: Router): MainNavigator = MainNavigatorImpl(router)
}