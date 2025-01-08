package com.zinaidasaevska.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    var isFravourite: Boolean
)
