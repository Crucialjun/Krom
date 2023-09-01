package ng.documenti.krom.features.animelist.states

import ng.documenti.krom.features.animelist.domain.entities.AnimeModel

data class FeaturedAnimeState(
    val featuredAnime: AnimeModel? = null ,
    val isLoading: Boolean = false,
    val error: String = ""

)
