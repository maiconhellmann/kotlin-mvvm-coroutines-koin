package com.maiconhellmann.architecture.injection.module

import com.maiconhellmann.architecture.data.RateRepository
import org.koin.dsl.module.applicationContext

val repositoryModule = applicationContext {
    factory { RateRepository(get(), get()) }
}