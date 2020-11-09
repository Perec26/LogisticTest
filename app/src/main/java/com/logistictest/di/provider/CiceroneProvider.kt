package com.logistictest.di.provider

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Provider

class CiceroneProvider : Provider<Cicerone<Router>> {
    override fun get() = INSTANCE

    companion object {
        private val INSTANCE = Cicerone.create()
    }
}