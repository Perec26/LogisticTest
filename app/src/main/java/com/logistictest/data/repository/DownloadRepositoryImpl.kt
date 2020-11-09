package com.logistictest.data.repository

import android.app.DownloadManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.logistictest.domain.model.Cat
import com.logistictest.domain.repository.DownloadRepository
import java.io.File
import java.io.FileOutputStream


class DownloadRepositoryImpl(
    private val downloadManager: DownloadManager,
    private val context: Context
) : DownloadRepository {
    override fun downloadImage(cat: Cat) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            downloadWithDownloadManager(cat)
        } else {
            downloadWithOutDownloadManager(cat)
        }
    }

    private fun downloadWithDownloadManager(cat: Cat) {
        val uri = Uri.parse(cat.url)
        DownloadManager.Request(uri)
            .setVisibleInDownloadsUi(true)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, cat.id)
            .apply { downloadManager.enqueue(this) }
    }

    private fun downloadWithOutDownloadManager(cat: Cat) {
        val uri = Uri.parse(cat.url)
        Glide.with(context)
            .asBitmap()
            .load(uri)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    saveBitmapToCache(cat.id, resource)
                }
            })
    }


    private fun saveBitmapToCache(fileName: String, bitmap: Bitmap?) {
        val root: String =
            Environment.getExternalStorageDirectory().toString() + "/Download/"
        val image = File(root, "$fileName.png")
        try {
            val outputStream = FileOutputStream(image)
            bitmap!!.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

}