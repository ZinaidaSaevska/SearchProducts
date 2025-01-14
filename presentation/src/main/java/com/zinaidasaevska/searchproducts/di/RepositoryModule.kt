package com.zinaidasaevska.searchproducts.di

import com.zinaidasaevska.data.repository.ProductsRepositoryImpl
import com.zinaidasaevska.data.util.FavouritesMapper
import com.zinaidasaevska.data.util.ProductEntityMapper
import com.zinaidasaevska.data.util.ProductMapper
import com.zinaidasaevska.domain.repository.ProductsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductsRepository> {
        ProductsRepositoryImpl(
            api = get(),
            productsDb = get(),
            productEntityMapper = ProductEntityMapper(),
            productMapper = ProductMapper(),
            favouritesMapper = FavouritesMapper()
        )
    }
}