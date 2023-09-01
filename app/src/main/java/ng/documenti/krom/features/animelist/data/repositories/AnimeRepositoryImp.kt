package ng.documenti.krom.features.animelist.data.repositories

import ng.documenti.krom.features.animelist.data.dataSources.AnimeDetailsDto
import ng.documenti.krom.features.animelist.data.dataSources.JikanApi
import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.TopAnimeDto
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams
import javax.inject.Inject


class AnimeRepositoryImp @Inject constructor(
    private val animeApi: JikanApi,

) : AnimeRepository {
    override suspend fun getTopAnime(
       params: FetchTopAnimeParams
    ): TopAnimeDto {
        return animeApi.getTopAnime(
            type = params.type,
            filter = params.filter,
            limit = params.limit,
            page = params.page,
            rating = params.rating,
            sfw = params.sfw
        )
    }

    override suspend fun getAnime(id: Int): AnimeDetailsDto {
        return  animeApi.getAnime(id)
    }

    override suspend fun getFeaturedAnime(): AnimeDetailsDto {
        return animeApi.getFeaturedAnime()
    }
}
