package com.zinaidasaevska.domain.usecases

import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.domain.repository.ProductsRepository
import com.zinaidasaevska.domain.util.Resource
import kotlinx.coroutines.CancellationException

class SearchProductsUseCase(private val repository: ProductsRepository):
    BaseUseCase<SearchProductsUseCase.Params, Resource<List<Product>>>() {

    class Params(val query: String)

    override suspend fun run(params: Params): Resource<List<Product>> {
        return try {
            val response = repository.searchProducts(params.query)
            Resource.Success(response)
        } catch (ex: CancellationException) {
            throw ex
        } catch (ex: Exception) {
            Resource.Error(ex)
        }
    }
}