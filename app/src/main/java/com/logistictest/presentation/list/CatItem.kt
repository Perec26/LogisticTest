package com.logistictest.presentation.list

import android.content.Context
import android.graphics.PorterDuff
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.logistictest.R
import kotlinx.android.synthetic.main.item_cat.view.*


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CatItem(context: Context) : LinearLayout(context) {
    init {
        inflate(context, R.layout.item_cat, this)
    }

    @ModelProp
    fun image(url: String) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_image)
            .transition(DrawableTransitionOptions.with { dataSource, isFirstResource ->
                if (dataSource === DataSource.RESOURCE_DISK_CACHE) null
                else DrawableCrossFadeFactory.Builder()
                    .build()
                    .build(dataSource, isFirstResource)
            }
            )
            .into(ivImage)
    }

    @ModelProp
    fun favorite(isFavorite: Boolean) {
        val tintRes = if (isFavorite) R.color.colorIconYellow else R.color.colorIconGrey
        val color = ContextCompat.getColor(context, tintRes)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ivFavorite.drawable.setTint(color)
        } else {
            ivFavorite.drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }


    @CallbackProp
    fun onClick(callback: (() -> Unit)?) {
        ivImage.setOnClickListener { callback?.invoke() }
    }

    @CallbackProp
    fun onDownLoadClick(callback: (() -> Unit)?) {
        ivDownload.setOnClickListener { callback?.invoke() }
    }
}