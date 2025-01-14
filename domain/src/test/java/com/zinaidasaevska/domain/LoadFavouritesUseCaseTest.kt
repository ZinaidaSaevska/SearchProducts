package com.zinaidasaevska.domain

import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.usecases.LoadFavouritesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class LoadFavouritesUseCaseTest {

    private val repository: ProductsRepository = mockk(relaxed = true)

    //Subject Under Test
    private val sut = LoadFavouritesUseCase(repository)

    @Test
    fun `test corresponding repository method was called`() {
        //Given
        val resultList = listOf(Product(id = 1, title = "Item title", description = "Description", thumbnail = "", isFavourite = false))
        coEvery { repository.loadFavouriteProducts() } returns resultList

        //When
        val result = runBlocking { sut.run() }

        //Then
        coVerify { repository.loadFavouriteProducts() }
        assertEquals(result, resultList)
    }
}