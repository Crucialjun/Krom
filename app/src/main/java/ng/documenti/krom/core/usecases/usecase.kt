import kotlinx.coroutines.flow.Flow

interface UseCases<T, Params> {
    suspend operator fun invoke(params: Params): T
}

interface StreamUseCases<T, Params> {
    fun invoke(params: Params): Flow<T>
}

class NoParams

fun main() {
    val noParams = NoParams()
    println(noParams)
}