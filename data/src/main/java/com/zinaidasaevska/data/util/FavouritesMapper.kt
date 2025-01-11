package com.zinaidasaevska.data.util

import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.domain.model.Product

class FavouritesMapper: BaseMapper<ProductEntity, Product>() {

    override fun mapFrom(from: ProductEntity): Product {
        return Product(
            id = from.id,
            title = from.title,
            description = from.description,
            thumbnail = from.thumbnail,
            isFavourite = from.isFravourite
        )
    }
}