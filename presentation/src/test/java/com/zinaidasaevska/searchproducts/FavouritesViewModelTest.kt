package com.zinaidasaevska.searchproducts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.usecases.LoadFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import com.zinaidasaevska.searchproducts.favourites.FavouritesViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class FavouritesViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository: ProductsRepository = mockk(relaxed = true)

    private val removeFromFavouritesUseCase = RemoveFromFavouritesUseCase(repository)
    private val loadFavouritesUseCase = LoadFavouritesUseCase(repository)

    private lateinit var sut: FavouritesViewModel

    @Before
    fun setup() {
        sut = spyk(FavouritesViewModel(loadFavouritesUseCase, removeFromFavouritesUseCase))
    }

    @Test
    fun `test favourite items are loaded on initialization`() {
        //Given
        val favouritesList = listOf(
            Product(
                id = 1,
                title = "Item title",
                description = "Description",
                thumbnail = "",
                isFavourite = false
            )
        )
        coEvery { loadFavouritesUseCase.run() } returns favouritesList

        //When
        sut = FavouritesViewModel(loadFavouritesUseCase, removeFromFavouritesUseCase)

        //Then
        val result = sut.favouritesUiState.value.favouritesList

        assertEquals(favouritesList, result)
    }

    @Test
    fun `test removeFromFavourites calls UseCase`() {
        //Given
        val productId = 1

        //When
        sut.removeFromFavourites(productId)

        //Then
        coVerify(exactly = 1) { removeFromFavouritesUseCase.run(RemoveFromFavouritesUseCase.Params(productId)) }
    }
}