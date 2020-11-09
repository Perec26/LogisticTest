package com.logistictest.domain.interactor

import com.logistictest.domain.model.Cat
import com.logistictest.domain.repository.CatRepository
import com.logistictest.domain.repository.DownloadRepository
import javax.inject.Inject

class CatInteractor @Inject constructor(
    private val catRepository: CatRepository,
    private val downloadRepository: DownloadRepository
) {
    fun getCat(page: Int) = catRepository.loadCats(page)
    fun getFavoriteCat() = catRepository.getFavoriteCats()
    fun saveCat(cat: Cat) = catRepository.saveCat(cat)
    fun deleteCat(cat: Cat) = catRepository.deleteCat(cat)
    fun downloadImage(cat: Cat) = downloadRepository.downloadImage(cat)
}