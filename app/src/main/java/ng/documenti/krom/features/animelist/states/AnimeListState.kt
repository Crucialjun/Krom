package ng.documenti.krom.features.animelist.states

import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.AnimeDto
import ng.documenti.krom.features.animelist.domain.entities.AnimeModel

data class AnimeListState(
    val animeList: List<AnimeModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
