package com.zinaidasaevska.searchproducts.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.usecases.AddToFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase
) : ViewModel() {

    fun addToFavourites(product: Product) {
        viewModelScope.launch {
            addToFavouritesUseCase.run(AddToFavouritesUseCase.Params(product))
        }
    }

    fun removeFromFavourites(productId: Int) {
        viewModelScope.launch {
            removeFromFavouritesUseCase.run(RemoveFromFavouritesUseCase.Params(productId))
        }
    }
}