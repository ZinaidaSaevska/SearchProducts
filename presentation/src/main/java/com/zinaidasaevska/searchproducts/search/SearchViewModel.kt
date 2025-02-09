package com.zinaidasaevska.searchproducts.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.usecases.AddToFavouritesUseCase
import com.zinaidasaevska.domain.usecases.RemoveFromFavouritesUseCase
import com.zinaidasaevska.domain.usecases.SearchProductsUseCase
import com.zinaidasaevska.domain.util.Resource
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase
) : ViewModel() {

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = _products

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _query: MutableLiveData<String> = MutableLiveData()
    val query: LiveData<String> = _query

    fun searchProducts(query: String) {
        _query.postValue(query)

        viewModelScope.launch {
            when (val response = searchProductsUseCase.run(SearchProductsUseCase.Params(query))) {
                is Resource.Success -> {
                    _products.postValue(response.data ?: emptyList())
                }

                is Resource.Error -> {
                    response.errorMessage?.let { message ->
                        _error.postValue(message)
                    }
                }
            }
        }
    }

    fun addProductToFavourites(product: Product) {
        viewModelScope.launch {
            addToFavouritesUseCase.run(AddToFavouritesUseCase.Params(product))
            toggleFavourite(product.id)
        }
    }

    fun removeFromFavourites(productId: Int) {
        viewModelScope.launch {
            removeFromFavouritesUseCase.run(RemoveFromFavouritesUseCase.Params(productId))
            toggleFavourite(productId)
        }
    }

    private fun toggleFavourite(productId: Int) {
        _products.value = _products.value?.map { product ->
            if (product.id == productId) {
                product.copy(isFavourite = !product.isFavourite)
            } else
                product
        }
    }
}