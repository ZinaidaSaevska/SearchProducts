package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.domain.model.Favourite
import com.zinaidasaevska.domain.repository.ProductsRepository

class LoadFavouritesUseCase(private val repository: ProductsRepository): BaseUseCase<Any, List<Favourite>>() {
    override suspend fun run(params: Any): List<Favourite> {
        return repository.loadFavouriteProducts()
    }
}