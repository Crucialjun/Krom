

class FetchAnimeUseCase : UseCases<String, NoParams>{
    override suspend fun invoke(params: NoParams): String {
        return "Hello"
    }
}


