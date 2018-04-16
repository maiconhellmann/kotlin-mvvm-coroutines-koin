package com.maiconhellmann.architecture.injection.module

import com.maiconhellmann.architecture.data.local.MovieDatabase
import com.maiconhellmann.architecture.data.local.MovieLocalDataSource
import org.koin.dsl.module.applicationContext

val localDataSourceModule = applicationContext {

    factory { MovieLocalDataSource(get()) }
    factory { MovieDatabase.getInstance(get()) }
}