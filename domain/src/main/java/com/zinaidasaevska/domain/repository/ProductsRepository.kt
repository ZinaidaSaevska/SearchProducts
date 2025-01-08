package com.zinaidasaevska.domain.repository

import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.data.model.Product

interface ProductsRepository {

    //API call
    suspend fun searchProducts(query: String): List<Product>

    //DB call
    suspend fun addProduct(product: ProductEntity)

    suspend fun removeProduct(productId: Int)

    suspend fun loadFavouriteProducts(): List<ProductEntity>
}