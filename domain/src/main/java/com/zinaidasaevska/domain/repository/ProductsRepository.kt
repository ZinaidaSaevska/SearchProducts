package com.zinaidasaevska.domain.repository

import com.zinaidasaevska.domain.model.Product

interface ProductsRepository {

    //API call
    suspend fun searchProducts(query: String): List<Product>

    //DB call
    suspend fun addProduct(product: Product)

    suspend fun removeProduct(productId: Int)

    suspend fun loadFavouriteProducts(): List<Product>
}