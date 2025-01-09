package com.zinaidasaevska.searchproducts.di

import okhttp3.OkHttpClient
import com.zinaidasaevska.searchproducts.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createNetworkClient(baseUrl: String) =
    buildRetrofit(baseUrl, buildOkHttpClient())

private fun buildOkHttpClient(): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        clientBuilder.addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT))
    }

    return clientBuilder.build()
}

private fun buildRetrofit(baseUrl: String, httpClent: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClent)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}