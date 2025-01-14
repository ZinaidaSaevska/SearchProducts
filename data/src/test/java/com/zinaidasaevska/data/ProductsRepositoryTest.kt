package com.zinaidasaevska.data

import com.zinaidasaevska.data.api.ProductsApi
import com.zinaidasaevska.data.db.ProductsDatabase
import com.zinaidasaevska.data.repository.ProductsRepositoryImpl
import com.zinaidasaevska.data.util.FavouritesMapper
import com.zinaidasaevska.data.util.ProductEntityMapper
import com.zinaidasaevska.data.util.ProductMapper
import com.zinaidasaevska.domain.model.Product
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert
import org.junit.Test

class ProductsRepositoryTest : MockWebServerTest() {
    private val productsApi = retrofit.create(ProductsApi::class.java)
    private val productsDatabase: ProductsDatabase = mockk(relaxed = true)
    private val productEntityMapper = ProductEntityMapper()
    private val productMapper = ProductMapper()
    private val favouritesMapper = FavouritesMapper()

    private val sut = ProductsRepositoryImpl(
        productsApi,
        productsDatabase,
        productEntityMapper, productMapper, favouritesMapper
    )

    @Test
    fun `test search products returns success response`() {
        val query = "query"
        mockWebServer.enqueueResponse("searchProductResponse.json", 200)

        val expectedResult =
            Product(
                id = 101,
                title = "Apple AirPods Max Silver",
                description = "The Apple AirPods Max in Silver are premium over-ear headphones with high-fidelity audio, adaptive EQ, and active noise cancellation. Experience immersive sound in style.",
                thumbnail = "https://cdn.dummyjson.com/products/images/mobile-accessories/Apple%20AirPods%20Max%20Silver/thumbnail.png",
                isFavourite = false
            )

        runBlocking {
            val result = sut.searchProducts(query)
            Assert.assertEquals(listOf(expectedResult), result)
        }

        val request: RecordedRequest = mockWebServer.takeRequest()
        Assert.assertEquals("/search?q=${query}", request.path)
    }
}