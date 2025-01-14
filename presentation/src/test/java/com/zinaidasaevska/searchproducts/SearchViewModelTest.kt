package com.zinaidasaevska.searchproducts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.usecases.AddToFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import com.zinaidasaevska.domain.usecases.SearchProductsUseCase
import com.zinaidasaevska.domain.util.Resource
import com.zinaidasaevska.searchproducts.search.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SearchViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository: ProductsRepository = mockk(relaxed = true)

    private val searchProductsUseCase = SearchProductsUseCase(repository)
    private val addToFavouritesUseCase = AddToFavouritesUseCase(repository)
    private val removeFromFavouritesUseCase = RemoveFromFavouritesUseCase(repository)

    //Subject Under Test
    private lateinit var sut: SearchViewModel

    @Before
    fun setup() {
        sut = spyk(
            SearchViewModel(
                searchProductsUseCase = searchProductsUseCase,
                addToFavouritesUseCase = addToFavouritesUseCase,
                removeFromFavouritesUseCase = removeFromFavouritesUseCase
            )
        )
    }

    @Test
    fun `test searchProduct returns Success result when request is successful`() {
        //Given
        val resultList = listOf(
            Product(
                id = 1,
                title = "Item title",
                description = "Description",
                thumbnail = "",
                isFavourite = false
            )
        )
        val query = "query"

        coEvery { searchProductsUseCase.run(SearchProductsUseCase.Params(query)) } returns Resource.Success(
            resultList
        )

        //When
        sut.searchProducts(query)

        //Then
        sut.products.observeForever { result ->
            assertNotNull(result)
            assertEquals(resultList, result)
        }
    }

    @Test
    fun `test searchProduct returns Error result when request fails`() {
        //Given
        val query = "query"
        val errorMessage = "Error Message"

        coEvery { repository.searchProducts(query) } throws Exception(errorMessage)

        //When
        sut.searchProducts(query)

        //Then
        sut.products.observeForever { result ->
            assertNotNull(result)
        }

        sut.error.observeForever { error ->
            assertNotNull(error)
            assertEquals(errorMessage, error)
        }
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
        sut.addProductToFavourites(product)

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