package com.zinaidasaevska.searchproducts.di

import androidx.room.Room
import com.zinaidasaevska.data.db.ProductsDatabase
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication


val persistenceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ProductsDatabase::class.java,
            ProductsDatabase.DATABASE_FILE_NAME
        )
            .build()
    }
}