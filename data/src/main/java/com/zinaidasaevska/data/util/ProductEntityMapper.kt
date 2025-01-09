package com.zinaidasaevska.data.util

import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.domain.model.Favourite

class ProductEntityMapper: BaseMapper<Favourite, ProductEntity>() {
    override fun mapFrom(from: Favourite): ProductEntity {
        return ProductEntity(
            id = from.id,
            title = from.title,
            description = from.description,
            thumbnail = from.thumbnail,
            isFravourite = from.isFravourite
        )
    }
}