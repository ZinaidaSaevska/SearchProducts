package com.zinaidasaevska.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zinaidasaevska.data.db.entities.ProductEntity

@Dao
interface FavouriteProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductEntity)

    @Query("DELETE FROM productentity WHERE id = :productId")
    suspend fun removeProduct(productId: Int)

    @Query("SELECT * FROM productentity")
    suspend fun loadFavouriteProducts(): List<ProductEntity>
}