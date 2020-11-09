package com.logistictest.domain.repository

import com.logistictest.domain.model.Cat

interface DownloadRepository {
    fun downloadImage(cat: Cat)
}