package com.zinaidasaevska.searchproducts.di

import com.zinaidasaevska.searchproducts.favourites.FavouritesViewModel
import com.zinaidasaevska.searchproducts.productdetails.ProductDetailsViewModel
import com.zinaidasaevska.searchproducts.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SearchViewModel(
            searchProductsUseCase = get(),
            addToFavouritesUseCase = get(),
            removeFromFavouritesUseCase = get()
        )
    }

    viewModel {
        FavouritesViewModel(
            loadFavouritesUseCase = get(),
            removeFromFavouritesUseCase = get()
        )
    }

    viewModel {
        ProductDetailsViewModel(
            addToFavouritesUseCase = get(),
            removeFromFavouritesUseCase = get()
        )
    }
}