package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.domain.repository.ProductsRepository

class AddToFavouritesUseCase(private val repository: ProductsRepository): BaseUseCase<AddToFavouritesUseCase.Params, Unit>() {

    class Params(val productEntity: ProductEntity)

    override suspend fun run(params: Params) {
        repository.addProduct(params.productEntity)
    }
}