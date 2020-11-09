package com.logistictest.presentation.catlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.logistictest.R
import com.logistictest.base.PageScrollListener
import com.logistictest.di.component.DaggerCatListComponent
import com.logistictest.di.component.DaggerMainComponent
import com.logistictest.di.module.ContextModule
import com.logistictest.presentation.list.CatController
import com.logistictest.presentation.model.CatUi
import kotlinx.android.synthetic.main.fragment_catlist.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class CatListFragment : MvpAppCompatFragment(), CatListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: CatListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var isLoading = false

    private val controller by lazy {
        CatController(
            showLoading = true,
            onCatClick = { presenter.onCatClick(it) },
            onDownloadClick = { presenter.onDownloadClick(it) }
        )
    }

    private val pageListener by lazy {
        object : PageScrollListener() {
            override fun isLoading() = isLoading
            override fun loadNextPage() {
                isLoading = true
                presenter.loadNextPage()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val mainComponent = DaggerMainComponent.builder()
            .contextModule(ContextModule(requireContext()))
            .build()
        DaggerCatListComponent.builder()
            .mainComponent(mainComponent)
            .build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCats.setController(controller)
        rvCats.addOnScrollListener(pageListener)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_favorites) presenter.onFavoritesClick()
            true
        }
    }

    override fun showLoading() {
        pbLoading.isVisible = true
    }

    override fun hideLoading() {
        pbLoading.isVisible = false
    }

    override fun showCats(cats: List<CatUi>) {
        isLoading = false
        controller.setData(cats)
    }

    override fun showError() {
        Snackbar.make(clContainer, R.string.error, Snackbar.LENGTH_LONG)
            .setAction(R.string.retry) { presenter.onRetryClick() }
            .show()
    }
}