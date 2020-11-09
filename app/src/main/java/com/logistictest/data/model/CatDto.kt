package com.logistictest.data.model

import com.logistictest.domain.model.Cat

data class CatDto(
    val breeds: List<Any>,
    val categories: List<Category>? = listOf(),
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)

data class Category(
    val id: Int,
    val name: String
)

fun CatDto.toDomain() = Cat(id, url)

fun List<CatDto>.toDomain() = map { it.toDomain() }
