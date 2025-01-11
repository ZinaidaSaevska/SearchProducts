package com.zinaidasaevska.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    var isFavourite: Boolean
)
