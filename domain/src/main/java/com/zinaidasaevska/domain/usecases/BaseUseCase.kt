package com.zinaidasaevska.domain.usecases

abstract class BaseUseCase<in Params, out Type> where Type: Any {
    abstract suspend fun run(params: Params? = null): Type

    suspend operator fun invoke(params: Params? = null): Type = run(params)
}