package com.zinaidasaevska.searchproducts.di

import com.zinaidasaevska.domain.usecases.AddToFavouritesUseCase
import com.zinaidasaevska.domain.usecases.LoadFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import com.zinaidasaevska.domain.usecases.SearchProductsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AddToFavouritesUseCase(get()) }

    single { LoadFavouritesUseCase(get()) }

    single { RemoveFromFavouritesUseCase(get()) }

    single { SearchProductsUseCase(get()) }

}