package com.zinaidasaevska.data.repository

import com.zinaidasaevska.data.api.ProductsApi
import com.zinaidasaevska.data.db.ProductsDatabase
import com.zinaidasaevska.data.util.FavouritesMapper
import com.zinaidasaevska.data.util.ProductEntityMapper
import com.zinaidasaevska.data.util.ProductMapper
import com.zinaidasaevska.domain.model.Favourite
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val api: ProductsApi,
    private val productsDb: ProductsDatabase,
    private val productEntityMapper: ProductEntityMapper,
    private val productMapper: ProductMapper,
    private val favouritesMapper: FavouritesMapper
) : ProductsRepository {

    private val productsDao = productsDb.favouritesDao()

    override suspend fun searchProducts(query: String): List<Product> {
        val products = api.searchProducts(query).body()?.products?.map { productResponse ->
            productMapper.mapFrom(productResponse)
        }
        return products ?: emptyList()
    }

    override suspend fun addProduct(favourite: Favourite) {
        val productEntity = productEntityMapper.mapFrom(favourite)
        productsDao.addProduct(productEntity)
    }

    override suspend fun removeProduct(productId: Int) {
        productsDao.removeProduct(productId)
    }

    override suspend fun loadFavouriteProducts(): List<Favourite> {
        val favourites = productsDao.loadFavouriteProducts().map { productEntity ->
            favouritesMapper.mapFrom(productEntity)
        }

        return favourites
    }
}