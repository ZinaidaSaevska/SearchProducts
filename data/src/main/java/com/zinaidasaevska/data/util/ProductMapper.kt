package com.zinaidasaevska.data.util

import com.zinaidasaevska.data.model.ProductResponse
import com.zinaidasaevska.domain.model.Product

class ProductMapper: BaseMapper<ProductResponse, Product>() {

    override fun mapFrom(from: ProductResponse): Product {
        return Product(
            id = from.id,
            title = from.title,
            description = from.description,
            thumbnail = from.thumbnail
        )
    }
}