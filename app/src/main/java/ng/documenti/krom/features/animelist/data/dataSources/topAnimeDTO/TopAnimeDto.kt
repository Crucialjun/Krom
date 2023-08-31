package ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO

data class TopAnimeDto(
    val `data`: List<AnimeDto>,
    val pagination: Pagination
)