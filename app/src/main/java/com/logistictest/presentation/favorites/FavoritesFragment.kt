package com.logistictest.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.logistictest.R
import com.logistictest.di.component.DaggerFavoritesComponent
import com.logistictest.di.component.DaggerMainComponent
import com.logistictest.di.module.ContextModule
import com.logistictest.presentation.list.CatController
import com.logistictest.presentation.model.CatUi
import kotlinx.android.synthetic.main.fragment_favorites.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FavoritesFragment : MvpAppCompatFragment(), FavoritesView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val controller by lazy {
        CatController(
            showLoading = false,
            onCatClick = { presenter.onCatClick(it) },
            onDownloadClick = { presenter.onDownloadClick(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val mainComponent = DaggerMainComponent.builder()
            .contextModule(ContextModule(requireContext()))
            .build()
        DaggerFavoritesComponent.builder()
            .mainComponent(mainComponent)
            .build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackPressed() }
        rvCats.setController(controller)
    }

    override fun showLoading() {
        pbLoading.isVisible = true
    }

    override fun hideLoading() {
        pbLoading.isVisible = false
    }

    override fun showCats(cats: List<CatUi>) {
        controller.setData(cats)
    }
}