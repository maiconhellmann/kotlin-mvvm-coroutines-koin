package com.maiconhellmann.architecture.injection.module

import com.maiconhellmann.architecture.data.local.RateDatabase
import com.maiconhellmann.architecture.data.local.RateLocalDataSource
import org.koin.dsl.module.applicationContext

val localDataSourceModule = applicationContext {

    factory { RateLocalDataSource(get()) }
    factory { RateDatabase.getInstance(get()) }
}