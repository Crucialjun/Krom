package ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO

data class TopAnimeDto(
    val `data`: List<AnimeDto>,
    val pagination: Pagination
)