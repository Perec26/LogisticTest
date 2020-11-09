package com.logistictest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.logistictest.domain.model.Cat

const val CAT_TABLE = "cats"

@Entity(tableName = CAT_TABLE)
data class CatEntity(
    @PrimaryKey
    val id: String,
    val url: String,
)

fun CatEntity.toDomain() = Cat(id, url)

fun List<CatEntity>.toDomain() = map { it.toDomain() }

fun Cat.toEntity() = CatEntity(id, url)