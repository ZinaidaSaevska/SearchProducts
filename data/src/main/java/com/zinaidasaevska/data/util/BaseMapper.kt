package com.zinaidasaevska.data.util

abstract class BaseMapper<T, E> {
    abstract fun mapFrom(from: T): E
}