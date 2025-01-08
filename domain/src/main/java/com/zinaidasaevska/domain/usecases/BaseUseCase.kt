package com.zinaidasaevska.domain.usecases

abstract class BaseUseCase<in Params, out Type> where Type: Any {
    abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(params: Params): Type = run(params)
}