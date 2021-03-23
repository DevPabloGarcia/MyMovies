package com.pablogarcia.my_movies.di

import android.app.Application
import com.pablogarcia.my_movies.di.modules.LocalRepositoryModule
import com.pablogarcia.my_movies.di.modules.UseCasesModule
import com.pablogarcia.my_movies.ui.movies.SharedMoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyMoviesApplication: Application() {

    private val viewModelModule = module {

        viewModel { SharedMoviesViewModel(get(), get(), get()) }
    }

    private val modules = listOf(
        viewModelModule,
        LocalRepositoryModule.module,
        UseCasesModule.module)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyMoviesApplication)
            modules(modules)
        }
    }
}
