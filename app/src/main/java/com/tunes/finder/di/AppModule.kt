package com.tunes.finder.di

import com.tunes.finder.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { SearchViewModel(get()) }

    single { createSearchRepository(get()) }

    single { createSearchUseCase(get()) }
}