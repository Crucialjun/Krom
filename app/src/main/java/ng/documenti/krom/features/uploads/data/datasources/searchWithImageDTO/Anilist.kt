package ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO

data class Anilist(
    val id: Int,
    val idMal: Int,
    val isAdult: Boolean,
    val synonyms: List<String>,
    val title: Title
)