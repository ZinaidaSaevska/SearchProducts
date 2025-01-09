package com.zinaidasaevska.searchproducts

import android.app.Application
import com.zinaidasaevska.searchproducts.di.networkModule
import com.zinaidasaevska.searchproducts.di.persistenceModule
import com.zinaidasaevska.searchproducts.di.repositoryModule
import com.zinaidasaevska.searchproducts.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidContext(this@SearchApplication)
            listOf(
                networkModule,
                persistenceModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}