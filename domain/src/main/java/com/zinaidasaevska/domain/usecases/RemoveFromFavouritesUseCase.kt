package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.domain.repository.ProductsRepository

class RemoveFromFavouritesUseCase(private val repository: ProductsRepository):
    BaseUseCase<RemoveFromFavouritesUseCase.Params, Unit>() {

    class Params(val productId: Int)

    override suspend fun run(params: Params?) {
        params?.let {
            repository.removeProduct(params.productId)
        }
    }
}