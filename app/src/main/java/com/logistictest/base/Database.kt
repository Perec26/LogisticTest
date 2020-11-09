package com.logistictest.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.logistictest.data.model.CatEntity
import com.logistictest.data.source.CatDao

@Database(
    entities = [CatEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun catDao(): CatDao
}