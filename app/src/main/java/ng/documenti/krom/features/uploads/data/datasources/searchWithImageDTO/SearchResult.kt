package ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO

data class SearchResult(
    val anilist: Anilist,
    val episode: Int,
    val filename: String,
    val from: Double,
    val image: String,
    val similarity: Double,
    val to: Double,
    val video: String
)