package com.logistictest.presentation.model

import com.logistictest.domain.model.Cat

data class CatUi(
    val id: String,
    val url: String,
    var isFavorite: Boolean
)

fun Cat.toUi(isFavorite: Boolean) = CatUi(id, url, isFavorite)

fun List<Cat>.toUi(isFavorite: Boolean) = map { it.toUi(isFavorite) }

fun CatUi.toDomain() = Cat(id, url)