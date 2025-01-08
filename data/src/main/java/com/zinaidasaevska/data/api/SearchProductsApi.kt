package com.zinaidasaevska.data.api

import com.zinaidasaevska.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchProductsApi {

    @GET("search")
    suspend fun searchProducts(@Query("q")query: String): Response<SearchResponse>
}