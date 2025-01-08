package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.domain.repository.ProductsRepository

class LoadFavouritesUseCase(private val repository: ProductsRepository): BaseUseCase<Any, List<ProductEntity>>() {
    override suspend fun run(params: Any): List<ProductEntity> {
        return repository.loadFavouriteProducts()
    }
}