package com.zinaidasaevska.domain

import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.usecases.AddToFavouritesUseCase
import io.mockk.called
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AddToFavouritesUseCaseTest {
    private val repository: ProductsRepository = mockk(relaxed = true)

    //Subject Under Test
    private val sut = AddToFavouritesUseCase(repository)

    @Test
    fun `test when params are valid corresponding repository method is called`() {
        //Given
        val product = Product(id = 1, title = "Item title", description = "Description", thumbnail = "", isFavourite = false)
        val params = AddToFavouritesUseCase.Params(product)

        //When
        runBlocking { sut.run(params) }

        //Then
        coVerify { repository.addProduct(product) }
    }

    @Test
    fun `test when params are not valid repository method is not called`() {
        //Given
        val params = null

        //When
        runBlocking { sut.run(params) }

        //Then
        coVerify() { repository wasNot called }
    }
}