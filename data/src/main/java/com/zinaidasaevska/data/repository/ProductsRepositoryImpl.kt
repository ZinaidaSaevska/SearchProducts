package com.zinaidasaevska.data.repository

import com.zinaidasaevska.data.api.ProductsApi
import com.zinaidasaevska.data.db.dao.FavouriteProductsDao
import com.zinaidasaevska.data.db.entities.ProductEntity
import com.zinaidasaevska.data.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val api: ProductsApi,
    private val productsDao: FavouriteProductsDao): ProductsRepository {

    override suspend fun searchProducts(query: String): List<Product> {
        return api.searchProducts(query).body()?.products ?: emptyList()
    }

    override suspend fun addProduct(product: ProductEntity) {
        productsDao.addProduct(product)
    }

    override suspend fun removeProduct(productId: Int) {
        productsDao.removeProduct(productId)
    }

    override suspend fun loadFavouriteProducts(): List<ProductEntity> {
        return productsDao.loadFavouriteProducts()
    }
}