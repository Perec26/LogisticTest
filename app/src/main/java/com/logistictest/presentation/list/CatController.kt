package com.logistictest.presentation.list

import com.airbnb.epoxy.TypedEpoxyController
import com.logistictest.presentation.model.CatUi


class CatController(
    private val showLoading: Boolean,
    private val onCatClick: (CatUi) -> Unit,
    private val onDownloadClick: (CatUi) -> Unit
) : TypedEpoxyController<List<CatUi>>() {
    override fun buildModels(data: List<CatUi>) {
        data.forEach { buildCatItem(it) }
        if (data.isNotEmpty() && showLoading) buildLoadingItem()
    }

    private fun buildCatItem(catUi: CatUi) {
        catItem {
            id(catUi.id)
            image(catUi.url)
            favorite(catUi.isFavorite)
            onClick { onCatClick(catUi) }
            onDownLoadClick { onDownloadClick(catUi) }
            spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 2 }
        }
    }

    private fun buildLoadingItem() {
        loadingItem {
            id(-1)
        }
    }
}