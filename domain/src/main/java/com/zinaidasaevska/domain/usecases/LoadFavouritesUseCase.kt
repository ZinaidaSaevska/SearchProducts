package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository

class LoadFavouritesUseCase(private val repository: ProductsRepository): BaseUseCase<Any?, List<Product>>() {
    override suspend fun run(params: Any?): List<Product> {
        return repository.loadFavouriteProducts()
    }
}