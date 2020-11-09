package com.logistictest.di.provider

import android.content.Context
import androidx.room.Room
import com.logistictest.base.Database
import javax.inject.Provider


class DatabaseProvider(private val context: Context) : Provider<Database> {
    override fun get() = getInstance(context)

    companion object {
        private var INSTANCE: Database? = null
        private fun getInstance(context: Context): Database {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, Database::class.java, "logic_db")
                    .build()
            }
            return INSTANCE!!
        }
    }
}