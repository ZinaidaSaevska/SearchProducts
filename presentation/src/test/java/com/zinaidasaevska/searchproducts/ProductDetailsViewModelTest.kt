package com.zinaidasaevska.searchproducts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.usecases.AddToFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import com.zinaidasaevska.searchproducts.productdetails.ProductDetailsViewModel
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductDetailsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository: ProductsRepository = mockk()

    private val addToFavouritesUseCase = AddToFavouritesUseCase(repository)
    private val removeFromFavouritesUseCase = RemoveFromFavouritesUseCase(repository)

    //Subject Under Test
    private lateinit var sut: ProductDetailsViewModel

    @Before
    fun setup() {
       sut = spyk(ProductDetailsViewModel(addToFavouritesUseCase, removeFromFavouritesUseCase))
    }

    @Test
    fun `test addProductToFavourites calls proper UseCase`() {
        //Given
        val product = Product(
            id = 1,
            title = "Item title",
            description = "Description",
            thumbnail = "",
            isFavourite = false
        )

        //When
        sut.addToFavourites(product)

        //Then
        coVerify(exactly = 1) {
            addToFavouritesUseCase.run(
                AddToFavouritesUseCase.Params(
                    product
                )
            )
        }
    }

    @Test
    fun `test removeFromFavourites calls proper UseCase`() {
        //Given
        val productId = 1

        //When
        sut.removeFromFavourites(productId)

        //Then
        coVerify(exactly = 1) {
            removeFromFavouritesUseCase.run(
                RemoveFromFavouritesUseCase.Params(
                    productId
                )
            )
        }
    }
}