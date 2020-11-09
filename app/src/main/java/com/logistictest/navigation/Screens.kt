package com.logistictest.navigation

import com.logistictest.presentation.catlist.CatListFragment
import com.logistictest.presentation.favorites.FavoritesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object CatListScreen : SupportAppScreen() {
        override fun getFragment() = CatListFragment()
    }

    object FavoritesScreen : SupportAppScreen() {
        override fun getFragment() = FavoritesFragment()
    }

}