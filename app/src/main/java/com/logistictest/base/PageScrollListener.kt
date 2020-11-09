package com.logistictest.base

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PageScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        if (!isLoading() && layoutManager is GridLayoutManager) {
            val totalItemCount = layoutManager.itemCount
            val newLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            if (totalItemCount <= newLastVisibleItemPosition + 3) {
                loadNextPage()
            }
        }
    }

    abstract fun isLoading(): Boolean
    abstract fun loadNextPage()
}