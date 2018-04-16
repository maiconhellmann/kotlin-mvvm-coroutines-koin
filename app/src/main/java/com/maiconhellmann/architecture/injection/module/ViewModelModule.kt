package com.maiconhellmann.architecture.injection.module

import com.maiconhellmann.architecture.view.main.MainViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {
    viewModel { MainViewModel(get()) }
}