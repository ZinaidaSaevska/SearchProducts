package com.zinaidasaevska.domain

import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.usecases.SearchProductsUseCase
import com.zinaidasaevska.domain.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchProductsUseCaseTest {
    private val repository: ProductsRepository = mockk()

    //Subject Under Test
    private val sut = SearchProductsUseCase(repository)

    @Test
    fun `test success is returned when params are valid`() {
        //Given
        val resultList = listOf(Product(id = 1, title = "Item title", description = "Description", thumbnail = "", isFavourite = false))
        val params = SearchProductsUseCase.Params("query")
        coEvery { repository.searchProducts(params.query) } returns resultList

        //When
        val result = runBlocking { sut.run(params) }

        //Then
        assertTrue(result is Resource.Success)
        assertFalse(result.data.isNullOrEmpty())
    }

    @Test
    fun `test error is returned when params are not valid`() {
        //Given
        val params = null

        //When
        val result = runBlocking { sut.run(params) }

        //Then
        val expectedErrorMessage = "Missing params"

        assertTrue(result is Resource.Error)
        assertEquals(expectedErrorMessage, result.errorMessage)
    }

    @Test
    fun `test error is returned when request fails`() {
        //Given
        val params = SearchProductsUseCase.Params("query")
        coEvery { repository.searchProducts(params.query) } throws Exception()

        //When
        val result = runBlocking { sut.run(params) }

        //Then
        assertTrue(result is Resource.Error)
    }
}