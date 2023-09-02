package ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO

data class SearchWithImageDTO(
    val error: String,
    val frameCount: Int,
    val result: List<SearchResult>
)