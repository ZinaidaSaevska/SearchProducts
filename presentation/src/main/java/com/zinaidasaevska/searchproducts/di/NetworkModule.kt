package com.zinaidasaevska.searchproducts.di

import com.zinaidasaevska.data.api.ProductsApi
import com.zinaidasaevska.searchproducts.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { createNetworkClient(BuildConfig.BASE_URL) }
    single { (get<Retrofit>()).create(ProductsApi::class.java) }
}