package ng.documenti.krom.core.usecases

import kotlinx.coroutines.flow.Flow
import ng.documenti.krom.common.Resource

interface UseCases<T, Params> {
    operator fun invoke(params: Params): Flow<Resource<T>>
}

interface StreamUseCases<T, Params> {
    fun invoke(params: Params): Flow<T>
}

class NoParams

fun main() {
    val noParams = NoParams()
    println(noParams)
}