package com.zinaidasaevska.searchproducts.di

import com.zinaidasaevska.domain.usecases.AddToFavouritesUseCase
import com.zinaidasaevska.domain.usecases.LoadFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import com.zinaidasaevska.domain.usecases.SearchProductsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AddToFavouritesUseCase(repository = get()) }

    single { LoadFavouritesUseCase(repository = get()) }

    single { RemoveFromFavouritesUseCase(repository = get()) }

    single { SearchProductsUseCase(repository = get()) }
}