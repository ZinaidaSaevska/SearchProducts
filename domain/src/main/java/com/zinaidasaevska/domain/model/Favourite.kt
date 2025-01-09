package com.zinaidasaevska.domain.model

data class Favourite (
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    var isFravourite: Boolean
)