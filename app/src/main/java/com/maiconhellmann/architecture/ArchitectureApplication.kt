package com.maiconhellmann.architecture

import android.app.Application
import com.maiconhellmann.architecture.injection.module.localDataSourceModule
import com.maiconhellmann.architecture.injection.module.remoteDatasourceModule
import com.maiconhellmann.architecture.injection.module.repositoryModule
import com.maiconhellmann.architecture.injection.module.viewModelModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class ArchitectureApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin(this, listOf(
                remoteDatasourceModule,
                localDataSourceModule,
                repositoryModule,
                viewModelModule))
    }
}