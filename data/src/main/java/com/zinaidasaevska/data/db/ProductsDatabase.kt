package com.zinaidasaevska.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zinaidasaevska.data.db.dao.FavouriteProductsDao
import com.zinaidasaevska.data.db.entities.ProductEntity


@Database(entities = [ProductEntity::class], version = 1, exportSchema = true)
abstract class ProductsDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_FILE_NAME = "searchDatabase.db"
    }

    abstract fun favouritesDao(): FavouriteProductsDao
}