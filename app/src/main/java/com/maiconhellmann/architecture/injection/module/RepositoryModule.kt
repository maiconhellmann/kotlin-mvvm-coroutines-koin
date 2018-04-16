package com.maiconhellmann.architecture.injection.module

import com.maiconhellmann.architecture.data.MovieRepository
import org.koin.dsl.module.applicationContext

val repositoryModule = applicationContext {
    factory { MovieRepository(get(), get()) }
}