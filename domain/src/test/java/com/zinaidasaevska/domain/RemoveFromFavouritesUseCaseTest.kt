package com.zinaidasaevska.domain

import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import io.mockk.called
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RemoveFromFavouritesUseCaseTest {
    private val repository: ProductsRepository = mockk(relaxed = true)

    //Subject Under Test
    private val sut = RemoveFromFavouritesUseCase(repository)

    @Test
    fun `test when params are valid corresponding repository method is called`() {
        //Given
        val productId = 1
        val params = RemoveFromFavouritesUseCase.Params(productId)

        //When
        runBlocking { sut.run(params) }

        //Then
        coVerify { repository.removeProduct(productId)}
    }

    @Test
    fun `test when params are null repository method is not  called`() {
        //Given
        val params = null

        //When
        runBlocking { sut.run(params) }

        //Then
        coVerify { repository wasNot called}
    }
}