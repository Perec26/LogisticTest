package com.logistictest.presentation.list

import android.content.Context
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelView
import com.logistictest.R


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LoadingItem(context: Context) : LinearLayout(context) {
    init {
        inflate(context, R.layout.item_loading, this)
    }
}
