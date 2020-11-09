package com.logistictest.navigation

import ru.terrakok.cicerone.Router

class MainNavigatorImpl(
    private val router: Router
) : MainNavigator {
    override fun toCatList() {
        router.newRootScreen(Screens.CatListScreen)
    }

    override fun toFavorites() {
        router.navigateTo(Screens.FavoritesScreen)
    }

    override fun exit() {
        router.exit()
    }
}