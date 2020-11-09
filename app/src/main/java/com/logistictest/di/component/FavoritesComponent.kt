package com.logistictest.di.component

import com.logistictest.di.module.RepositoryModule
import com.logistictest.presentation.favorites.FavoritesFragment
import com.logistictest.presentation.favorites.FavoritesPresenter
import dagger.Component

@Component(
    modules = [RepositoryModule::class],
    dependencies = [MainComponent::class]
)
interface FavoritesComponent {
    fun getPresenter(): FavoritesPresenter
    fun inject(fragment: FavoritesFragment)
}