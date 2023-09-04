package ng.documenti.krom.features.animelist.data.repositories

import ng.documenti.krom.features.animelist.data.dataSources.online.AnimeDetailsDto
import ng.documenti.krom.features.animelist.domain.entities.AnimeModel
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams

interface AnimeRepository {

    suspend fun getTopAnime(
        params: FetchTopAnimeParams,
    ) : List<AnimeModel>

    suspend fun getAnime(id: Int) : AnimeDetailsDto

    suspend fun getFeaturedAnime() : AnimeDetailsDto




}