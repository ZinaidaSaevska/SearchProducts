package com.zinaidasaevska.searchproducts

import android.app.Application
import com.zinaidasaevska.searchproducts.di.networkModule
import com.zinaidasaevska.searchproducts.di.persistenceModule
import com.zinaidasaevska.searchproducts.di.repositoryModule
import com.zinaidasaevska.searchproducts.di.useCaseModule
import com.zinaidasaevska.searchproducts.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SearchApplication)
            modules(
                networkModule,
                persistenceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}