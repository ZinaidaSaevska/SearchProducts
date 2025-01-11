package com.zinaidasaevska.data.util

import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.domain.model.Product

class ProductEntityMapper: BaseMapper<Product, ProductEntity>() {
    override fun mapFrom(from: Product): ProductEntity {
        return ProductEntity(
            id = from.id,
            title = from.title,
            description = from.description,
            thumbnail = from.thumbnail,
            isFravourite = from.isFavourite
        )
    }
}