package com.zinaidasaevska.data.util

import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.domain.model.Favourite

class FavouritesMapper: BaseMapper<ProductEntity, Favourite>() {

    override fun mapFrom(from: ProductEntity): Favourite {
        return Favourite(
            id = from.id,
            title = from.title,
            description = from.description,
            thumbnail = from.thumbnail,
            isFravourite = from.isFravourite
        )
    }
}