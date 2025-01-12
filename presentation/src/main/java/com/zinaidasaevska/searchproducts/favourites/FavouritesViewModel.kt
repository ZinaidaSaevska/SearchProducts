package com.zinaidasaevska.searchproducts.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinaidasaevska.domain.usecases.LoadFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val loadFavouritesUseCase: LoadFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase
) : ViewModel() {

    private val _favouritesUiState =
        MutableStateFlow(FavouritesUiState(favouritesList = emptyList()))
    val favouritesUiState: StateFlow<FavouritesUiState> = _favouritesUiState

    init {
        viewModelScope.launch {
            _favouritesUiState.update {
                it.copy(favouritesList = loadFavouritesUseCase())
            }
        }
    }

    fun removeFromFavourites(productId: Int) {
        viewModelScope.launch {
            removeFromFavouritesUseCase.run(RemoveFromFavouritesUseCase.Params(productId))
            val updatedList = favouritesUiState.value.favouritesList.filter { it.id != productId }
            _favouritesUiState.update {
                it.copy(
                    favouritesList = updatedList
                )
            }
        }
    }
}