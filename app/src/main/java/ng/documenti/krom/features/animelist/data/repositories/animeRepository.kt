package ng.documenti.krom.features.animelist.data.repositories

import ng.documenti.krom.features.animelist.data.dataSources.AnimeDetailsDto
import ng.documenti.krom.features.animelist.data.dataSources.JikanApiRating
import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.AnimeDto
import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.TopAnimeDto
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams

interface AnimeRepository {

    suspend fun getTopAnime(
        params: FetchTopAnimeParams
    ) : TopAnimeDto

    suspend fun getAnime(id: Int) : AnimeDetailsDto


}