package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository

class AddToFavouritesUseCase(private val repository: ProductsRepository): BaseUseCase<AddToFavouritesUseCase.Params, Unit>() {

    class Params(val product: Product)

    override suspend fun run(params: Params?) {
        params?.let {
            repository.addProduct(params.product)
        }
    }
}