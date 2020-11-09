package com.logistictest.di.component

import com.logistictest.di.module.RepositoryModule
import com.logistictest.presentation.catlist.CatListFragment
import com.logistictest.presentation.catlist.CatListPresenter
import dagger.Component

@Component(
    modules = [RepositoryModule::class],
    dependencies = [MainComponent::class]
)
interface CatListComponent {
    fun getPresenter(): CatListPresenter
    fun inject(fragment: CatListFragment)
}