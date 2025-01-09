package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.domain.model.Favourite
import com.zinaidasaevska.domain.repository.ProductsRepository

class AddToFavouritesUseCase(private val repository: ProductsRepository): BaseUseCase<AddToFavouritesUseCase.Params, Unit>() {

    class Params(val favourite: Favourite)

    override suspend fun run(params: Params) {
        repository.addProduct(params.favourite)
    }
}